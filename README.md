![logo](http://r.photo.store.qq.com/psb?/V11r2x9W13Hapo/lEmP8kslW7eO6EoeS2sDiG6joc52PdlYegWTHNgM9ZQ!/r/dK0AAAAAAAAA "logo")

##Mowa是什么 ?

-      随时随地在手机上用文字的方式记录所思所想，所见所得，并能免费永久的保存到云端的一款笔记APP。


------------



##Mowa有哪些功能？

* 支持个性文字笔记，图片笔记的记录
    *  点击主页+型菜单即可选择编辑文字笔记（迭代1.0版本实现）
    *  同样点击主页+型菜单即可选择图片即可加入笔记（迭代2.0版本将提供实现）

* 多类型笔记管理
 *  强大的隐私管理，私密笔记加锁 &
 *  同样点击主页+型菜单即可选择图片即可加入笔记

* 强大的文字定义功能，方便定制自己的喜欢的风格&

* 离线保存，WiFi模式自动保存到云端

* 所有选项笔记支持自动记忆&

* 好友间干货笔记分享

* 首页天气预报自动定位地区&



##### 带 & 是1.0版本实现功能，其余部分功能正开发中，暂时就记得这么这么几个了，后面再补上。


------------


###主界面部分截图：

#### 登录\主页
![login](http://a3.qpic.cn/psb?/V11r2x9W13Hapo/9Lho5KICGCwOqKTJH9jPgIWLlRlMTC91r6D5EWObChg!/b/dK0AAAAAAAAA&bo=4QCQAQAAAAADB1I!&rf=viewer_4 "login")=![home](http://a3.qpic.cn/psb?/V11r2x9W13Hapo/iLGNNfZwHqvwi194XUJhl9nEgnAeLXJyrRtoY5j7E4I!/b/dLAAAAAAAAAA&bo=4ACQAQAAAAADAFQ!&rf=viewer_4 "home")
#### 列表\发现
![list](http://a2.qpic.cn/psb?/V11r2x9W13Hapo/uNEAqNS4BbQejCHg3Bj1NSDcSx4f2dyKNVcM5dQYOK4!/b/dKkAAAAAAAAA&bo=4ACQAQAAAAADB1M!&rf=viewer_4 "list")=![find](http://a1.qpic.cn/psb?/V11r2x9W13Hapo/3B*rLemRGKuOq1ma9W*muMUsN.H57ytHH8AzNraN2EQ!/b/dP8AAAAAAAAA&bo=4ACQAQAAAAADB1M!&rf=viewer_4 "find")


------------




### 技术概述
- 界面实现：(ViewPager+Fragment+FragmentAdapter)+(Radiogroup)

- 数据缓存方案：sqlite+Picasso 前者负责实体类缓存持久化，后者缓存图片多媒体文件。 sqlite使用greenDAO框架实现。

- 后台使用Bmob后端云服务器，测试期间感觉还行，仅充值购买了一点验证短信，其余服务都是免费级别，觉得目前够用。

- 天气预报数据来自聚合数据，免费接口，限制请求次数；gson解析框架。

- 位置服务采用高德地图SDK


##有问题反馈
在使用中有问题，欢迎反馈给我，可以用以下联系方式跟我交流

- email: canrom7@outlook.com
* weibo: [@耳朵大胡图图](http://weibo.com/u/1947907272)





##感谢
感谢以下的项目,排名不分先后

* [greenDAO](http://greenrobot.org/greendao/)
* [ Picasso](http://square.github.io/picasso/)
* [ okhttp](http://square.github.io/okhttp/)
* [ gson](https://github.com/google/gson)
* [ XListView-Android](https://github.com/Maxwin-z/XListView-Android)

##关于作者

**一名初步入Android的个人开发者，此项目是在学习期间练手完成，个人精力有限，没有通过专业的测试，所以一定是存在一些小bug ，在往后的日子里，我将会根据反馈修复和更新下去，如对您造成不便影响，请见谅。
**

- 2016-08-12 18:54:13 星期五

## 协议


- Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License. You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

### End
