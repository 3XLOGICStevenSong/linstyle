document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	WeixinJSBridge.call('hideToolbar');
	WeixinJSBridge.call('hideOptionMenu');
});

//阻止浏览器后退按钮
//window.history.forward(1);

// 处理键盘事件 禁止后退键（backspace）密码或单行、多行文本框除外
function banbackspace(e) {
	var ev = e || window.event;// 获取event对象
	var obj = ev.target || ev.srcelement;// 获取事件源

	var t = obj.type || obj.getattribute('type');// 获取事件源类型

	// 获取作为判断条件的事件类型
	var vreadonly = obj.getattribute('readonly');
	var venabled = obj.getattribute('enabled');
	// 处理null值情况
	vreadonly = (vreadonly == null) ? false : vreadonly;
	venabled = (venabled == null) ? true : venabled;

	// 当敲backspace键时，事件源类型为密码或单行、多行文本的，
	// 并且readonly属性为true或enabled属性为false的，则退格键失效
	var flag1 = (ev.keycode == 8
			&& (t == "password" || t == "text" || t == "textarea") && (vreadonly == true || venabled != true)) ? true
			: false;

	// 当敲backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
	var flag2 = (ev.keycode == 8 && t != "password" && t != "text" && t != "textarea") ? true
			: false;

	// 判断
	if (flag2) {
		return false;
	}
	if (flag1) {
		return false;
	}
}

// 禁止后退键 作用于firefox、opera
document.onkeypress = banbackspace;
// 禁止后退键 作用于ie、chrome
document.onkeydown = banbackspace; 



function buttonHidden(button) {
	// window.document.body.className = "WAITING";
	button.disabled = true;
}

function saveName(id, eventId) {
	var el = document.getElementById(id);
	if (el != null) {
		el.name = eventId;
	}
}

function saveValue(id, value) {
	var el = document.getElementById(id);
	if (el != null) {
		el.value = value;
	}
}

function saveNameByName(name, eventId) {
	var el = document.all(name);
	if (el != null) {
		el.name = eventId;
	}
}

function saveValueByName(name, value) {
	var el = document.all(name);
	if (el != null) {
		el.value = value;
	}
}

function doButtonSubmit(form, eventId, submitField) {
	saveName(submitField, eventId);
	form.submit();
}

function formSubmitByPath(form,strPath){
	form.action= strPath;
}