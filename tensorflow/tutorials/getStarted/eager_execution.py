
from __future__ import absolute_import, division, print_function
import os
import matplotlib.pyplot as plt

import tensorflow as tf
import tensorflow.contrib.eager as tfe

#Enable eager execution
tf.enable_eager_execution()

#Tensorflow enviroment infos
print("Tensorflow version: {}".format(tf.VERSION))
print("Eager execution: {}".format(tf.executing_eagerly()))

#Method for reading a CSV line
def parse_csv(line):
    
    example_defaults = [[0.], [0.], [0.], [0.], [0]]
    parsed_line = tf.decode_csv(line, example_defaults)
    
    features = tf.reshape(parsed_line[:-1], shape=(4,))
    label = tf.reshape(parsed_line[-1], shape=())

    return features, label

def load_dataset_from_url(dataset_url):
    
    #Load the dataset from the internet
    dataset_fp = tf.keras.utils.get_file(fname=os.path.basename(dataset_url), origin=dataset_url)
    print("Local copy of the dataset file: {}".format(dataset_fp))

    #Configure the dataset
    dataset = tf.data.TextLineDataset(dataset_fp)
    dataset = dataset.skip(1)
    dataset = dataset.map(parse_csv)
    dataset = dataset.shuffle(buffer_size=1000)
    dataset = dataset.batch(32)

    return dataset

train_dataset = load_dataset_from_url("http://download.tensorflow.org/data/iris_training.csv")

#Just showing a sample of the dataset.
features, label = tfe.Iterator(train_dataset).next()
print("Example features: ", features[0])
print("Example label: ", label[0])

#Create the model 4 | 10 | 3
model = tf.keras.Sequential([
    tf.keras.layers.Dense(10, activation="relu", input_shape=(4,)),
    tf.keras.layers.Dense(10, activation="relu"),
    tf.keras.layers.Dense(3, activation="relu")
])

#Define loss function
def loss(model, x, y):
    y_ = model(x)
    return tf.losses.sparse_softmax_cross_entropy(labels=y, logits=y_)

#Define grad function
def grad(model, x, y):
    with tfe.GradientTape() as tape:
        loss_value = loss(model, x, y)
    return tape.gradient(loss_value, model.variables)

#Create optimizer
optimizer = tf.train.GradientDescentOptimizer(learning_rate=0.01)

train_loss_results = []
train_accuracy_results = []
num_epochs = 400

#Train the network
for epoch in range(num_epochs):
    
    epoch_loss_avg = tfe.metrics.Mean()
    epoch_accuracy = tfe.metrics.Accuracy()

    #Training loop using batches of 32
    for x, y in tfe.Iterator(train_dataset):

        grads = grad(model, x, y)
        optimizer.apply_gradients(zip(grads, model.variables), global_step=tf.train.get_or_create_global_step())

        epoch_loss_avg(loss(model, x, y))
        epoch_accuracy(tf.argmax(model(x), axis=1, output_type=tf.int32), y)

    train_loss_results.append(epoch_loss_avg.result())
    train_accuracy_results.append(epoch_accuracy.result())

    if (epoch % 50 == 0):
        print("Epoch {:03d}: Loss: {:.3f}, Accuracy: {:.3%}".format(epoch, epoch_loss_avg.result(), epoch_accuracy.result()))

#Plot the results

fig, axes = plt.subplots(2, sharex=True, figsize=(12, 8))
fig.suptitle("Training Metrics")

axes[0].set_ylabel("Loss", fontsize=14)
axes[0].plot(train_loss_results)

axes[1].set_ylabel("Accuracy", fontsize=14)
axes[1].set_xlabel("Epoch", fontsize=14)
axes[1].plot(train_accuracy_results)

plt.show()

#Validate model with the test dataset

test_dataset = load_dataset_from_url("http://download.tensorflow.org/data/iris_test.csv")
test_accuracy = tfe.metrics.Accuracy()

for (x, y) in tfe.Iterator(test_dataset):

    prediction =  tf.argmax(model(x), axis=1, output_type=tf.int32)
    test_accuracy(prediction, y)

print("Test set accuracy: {:.3%}".format(test_accuracy.result()))

#Do some predictions

class_ids = ["Iris setosa", "Iris versicolor", "Iris virginica"]
prediction_dataset = tf.convert_to_tensor([
    [5.1, 3.3, 1.7, 0.5,],
    [5.9, 3.0, 4.2, 1.5,],
    [6.9, 3.1, 5.4, 2.1]
])

predictions = model(prediction_dataset)

for i, logits in enumerate(predictions):
    class_idx = tf.argmax(logits).numpy()
    name = class_ids[class_idx]
    print("Example {} prediction: {}".format(i, name))