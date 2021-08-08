https://zhuanlan.zhihu.com/p/104307718


- 分布式线性模型
  - Logistic Regression
- 自动化特征工程
  - GBDT + LR，梯度提升决策树(Gradient Boosted Decision Tree)
- FM模型以及辩题
  - FM（Factorization Machines）
  - FFM（Field-aware Factorization Machines）
  - AFM（Attentional Factorization Machines）
- Embedding+MLP结构下的浅层改造
   - FNN（Factorization Machine supported Neural Network）
   - PNN（Product-based Neural Network）
   - NFM（Neural Factorization Machines）
   - ONN（Operation-aware Neural Networks）
- 双路并行的模型组合
   - wide&deep（Wide and Deep）
   - deepFM（Deep Factorization Machines）
- 复杂的显示特征交叉网络
   - DCN（Deep and Cross Network）
   - xDeepFM（Compressed Interaction Network）
   - AutoInt（Automatic Feature Interaction Learning）
- CTR预估模型总结与比较

## 1. 分布式线性模型
Logistic Regression

$$ f(x) = w_0 + \sum_{i=1}^n w_i x_i $$

## 2. 自动化特征工程
GBDT + LR （2014） -- 特征自动化时代的初探索
梯度提升决策树(Gradient Boosted Decision Tree)

## 3. FM模型及变体
### 3.1 FM：Factorization Machines, 2010 —— 隐向量学习提升模型表达

FM的公式包含了一阶线性部分与二阶特征交叉部分：
$$ f(x) = w_0 + \sum_{i=1}^n w_i x_i + \sum_{i=1}^n \sum_{j=i-1}^n \langle v_i, v_j \rangle x_i x_j $$

### 3.2 AFM：Attentional Factorization Machines, 2017 —— 引入Attention机制的FM

$$ \dot y = w_0 + \sum_{i=1}^n w_i x_i + p^T \sum_{i=1}^n \sum_{j=i+1}^n \alpha_ij (v_i \odot v_j) x_i y_j $$

## 4. Embedding+MLP结构下的浅层改造
使用复杂的操作让模型在浅层尽可能包含更多的信息，降低后续下游MLP的学习负担。
### 4.1 FNN： Factorisation Machine supported Neural Network, 2016 —— 预训练Embedding的NN模型
FNN是2016年提出的一种基于FM预训练Embedding的NN模型，其思路也比较简单；FM本身具备学习特征Embedding的能力，DNN具备**高阶特征交叉**的能力，因此将两者结合是很直接的思路。

### 4.2 PNN：Product-based Neural Network, 2016 —— 引入不同Product操作的Embedding层
### 4.3 NFM：Neural Factorization Machines, 2017 —— 引入Bi-Interaction Pooling结构的NN模型
### 4.4 ONN：Operation-aware Neural Network, 2019 —— FFM与NN的结合体

## 5. 双路并行的模型组合
以Wide&Deep和DeepFM为代表的模型架构都是采用了双路的结构。
例如Wide&Deep的左路为Embedding+MLP，右路为Cross Feature LR；DeepFM的左路为FM，右路为Embedding+MLP。
这类模型通过使用不同的模型进行**联合训练**，不同子模型之间互相弥补，增加整个模型信息表达和学习的多样性。

### 5.1 WDL：Wide and Deep Learning, 2016 —— Memorization与Generalization的信息互补
### 5.2 DeepFM：Deep Factorization Machines, 2017 —— FM基础上引入NN隐式高阶交叉信息

## 6. 复杂的显式特征交叉网络
无论是以FNN、PNN、NFM、ONN为代表的Embedding+MLP，还是以Wide&Deep和DeepFM为代表的双路模型，基本都是通过DNN来学习高阶特征交叉信息。
但DNN本身对于特征交叉是**隐式的**（Implicit）、bit-wise的，因此在这一阶段，以DCN、xDeepFM、AutoInt为代表的模型均把思路放在如何以**Explicit**的方式学习有限阶（bounded-degree）的特征交叉信息上。

### 6.1 Deep&Cross：Deep and Cross Network, 2017 —— 显式交叉网络Cross Net的诞生
### 6.2 xDeepFM：eXtreme Deep Factorization Machine, 2018 —— Compressed Interaction Network的诞生
提出了压缩交互网络
### 6.3 AutoInt：Automatic Feature Interaction Learning, 2019 —— 跨领域NLP技术的引入：Multi-head Self-attention提升模型表达


## 7. CTR预估模型总结与比较

![zhihu-ctr-model-history](./assets/zhihu-ctr-model-history.jpg)

CTR预估的发展趋势
- LR的主要限制在于需要大量**手动**特征工程来间接提高模型表达，此时出现了两个发展方向
  - 以FM为代表的端到端的隐向量学习方式，通过embedding来学习二阶交叉特征
  - 以GBDT+LR为代表的两阶段模型，第一阶段利用树模型优势自动化提取高阶特征交叉，第二阶段交由LR进行最终的学习
- 以FM为结点，出现了两个方向
  - 以FFM与AFM为代表的浅层模型改进。这两个模型本质上还是学习低阶交叉特征，只是在FM基础上为不同的交叉特征赋予的不同重要度
  - 深度学习时代到来，依附于DNN高阶交叉特征能力的Embedding+MLP结构开始流行
- 以Embedding+MLP为结点
  - Embedding层的改造+DNN进行高阶隐式学习，
  - 以W&D和DeepFM为代表的双路模型结构，将各个子模块算法的优势进行互补，
  - 显式高阶特征交叉网络的提出，

## 8.
LR
树模型自动化组合特征
端到端的Embedding+MLP结构
显式交叉网络
