
### Windows环境
1. 从 https://www.anaconda.com/download 安装 Python3.6版本的Anaconda。
2. 安装完毕后，从“开始”菜单中打开 Anaconda Promp
```sh
conda create -n mlcc pip python=3.6
conda activate mlcc
# 安装依赖
pip install -i https://pypi.tuna.tsinghua.edu.cn/simple --ignore-installed --upgrade tensorflow matplotlib pandas sklearn scipy seaborn

# 如果不成功过，可以尝试
pip install --ignore-installed --upgrade tensorflow matplotlib pandas sklearn scipy seaborn
pip install --upgrade tensorflow
pip install --upgrade sklearn

# 版本查看
pip show tensorflow
pip show matplotlib
pip show pandas
pip show sklearn
pip show scipy
pip show seaborn
```

3. 当所有软件包安装完毕后，从“开始”菜单中打开 Anaconda Navigator。
   - 切换到 mlcc 环境
   - 在 mlcc 环境安装 Jupyter 
   - 启动 Jupyter

### 从新手开始
导入 TensorFlow

```python
# 安装 TensorFlow
import tensorflow as tf
```
载入并准备好 MNIST 数据集。将样本从整数转换为浮点数：

```python
mnist = tf.keras.datasets.mnist

(x_train, y_train), (x_test, y_test) = mnist.load_data()
x_train, x_test = x_train / 255.0, x_test / 255.0
```
将模型的各层堆叠起来，以搭建 tf.keras.Sequential 模型。为训练选择优化器和损失函数：
```python
model = tf.keras.models.Sequential([
  tf.keras.layers.Flatten(input_shape=(28, 28)),
  tf.keras.layers.Dense(128, activation='relu'),
  tf.keras.layers.Dropout(0.2),
  tf.keras.layers.Dense(10, activation='softmax')
])

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])
```
训练并验证模型：
```python
model.fit(x_train, y_train, epochs=5)

model.evaluate(x_test,  y_test, verbose=2)
```

```
Epoch 1/5
1875/1875 [==============================] - 1s 796us/step - loss: 0.0414 - accuracy: 0.9865
Epoch 2/5
1875/1875 [==============================] - 1s 776us/step - loss: 0.0415 - accuracy: 0.9859
Epoch 3/5
1875/1875 [==============================] - 2s 801us/step - loss: 0.0356 - accuracy: 0.9879
Epoch 4/5
1875/1875 [==============================] - 2s 812us/step - loss: 0.0351 - accuracy: 0.9876
Epoch 5/5
1875/1875 [==============================] - 2s 1ms/step - loss: 0.0325 - accuracy: 0.9887
313/313 - 0s - loss: 0.0743 - accuracy: 0.9801
```
现在，这个照片分类器的准确度已经达到 98%。