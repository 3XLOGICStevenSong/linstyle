Chat模块开发手册

关于枚举值
一、消息签收状态

```java
public enum MsgSignFlagEnum {
	/**
	 * 未签收
	 */
	unsign(0, "未签收"),
	/**
	 * 已签收
	 */
	signed(1, "已签收");	
	
	public final Integer type;
	public final String content;
	
	MsgSignFlagEnum(Integer type, String content){
		this.type = type;
		this.content = content;
	}
}  
```

二、消息动作

```java
public enum MsgActionEnum {
   /**
    * 第一次(或重连)初始化连接
    */
   CONNECT(1, "第一次(或重连)初始化连接"),
   /**
    * 聊天消息
    */
   CHAT(2, "聊天消息"),
   /**
    * 消息签收
    */
   SIGNED(3, "消息签收"),
   /**
    * 客户端保持心跳
    */
   KEEPALIVE(4, "客户端保持心跳");

   public final Integer type;
   public final String content;
   
   MsgActionEnum(Integer type, String content){
      this.type = type;
      this.content = content;
   }
   
   public Integer getType() {
      return type;
   }  
}
```



关于发送WebSocket内容

请求方式：

```
ws://192.168.11.128:8099/ws      //默认端口8099
```

请求体格式：

```json
{
    "action": "",     						//参看上面MsgActionEnum类
    "chatMsg": {
        "senderId": "",    			//发送者工号
        "receiverId": "",  			//接收者工号
        "msg": "",				   		//消息内容
        "msgTime":"",						//消息发送时间
        "msgId": ""				   		//消息编号
        "senderName":"",					//发送者姓名
        "receiverName":""				//接收者姓名
        "senderAvatar"						//发送者头像
        
    },
    "extand": ""  //逗号分隔的消息ID,用于做签收
}
```

 1.发送创建SOCKET请求action为MsgActionEnum.CONNECT (1)
 2.发送聊天 action为MsgActionEnum.CHAT (2)
 3.消息签收 action为MsgActionEnum.SIGNED (3)
 4.心跳 action为MsgActionEnum.SIGNED (4) 心跳消息调用端必须设置消息12秒，建议设置成10秒

 

遗留问题：
​	1.用户在线状态：采用Redis+Schedule实现
​	2.并发量大的时候ID生成策略
​	