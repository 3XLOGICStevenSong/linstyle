var iScale = 1;
// devicePixelRatio 屏幕倍率
iScale = iScale / window.devicePixelRatio;
document.write('<meta name="viewport" content="width=device-width,initial-scale='+iScale+',minimum-scale='+iScale+',maximum-scale='+iScale+',user-scalable=no" />');

var iWidth = document.documentElement.clientWidth;

document.getElementsByTagName('html')[0].style.fontSize = iWidth / 10 + 'px';





