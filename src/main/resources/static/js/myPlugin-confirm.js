;(function ($){
	 $.fn.confirm=function (options){
		var defaults={
			content:"lalalla",//弹框内容
			height:"160px",//弹框高度
			width:"200px",//弹框宽度
			coverLayerOpacity:"0.2",//蒙层透明度
			coverLayerBg:"#2e3a4b",//蒙层背景色
			closeImgUrl:'',//关闭图片路径，不传则不显示
			buttons:[//按钮文字
			{ text: '关闭', abc: function () {
				coverLayer.remove();
		  	    dialog.remove();
			} },
			
			{ text: '跳转', abc: function () {
				window.location.href="www.baidu.com"
			} }
			],
			testEvent:function (i){alert(i)},
			buttonColor:'white',//按钮文字颜色
			buttonBg:'#0FAEE4',//按钮背景色
		}
		var options=$.extend(defaults,options);
		
		var coverLayer=$('<div></div>').addClass('cover-layer').css({"opacity":options.coverLayerOpacity,"background":options.coverLayerBg}).appendTo(document.body),
			dialog=$('<div></div>').addClass('dialog').appendTo(document.body),
			alertContent=$('<div></div>').addClass('alert-content').html(options.content).appendTo(dialog);
		//是否显示按钮 以及按钮有参数
		if(options.buttons!=null&&options.buttons.length>0){
		 var buttonContent=$("<div></div>").addClass('button-content').appendTo(dialog);
		 $.each(options.buttons,function (i,_button){//遍历buttons的参数
		 	$('<button></button>').addClass('alert-btn').text(_button.text).css({"color":options.buttonColor,"background":options.buttonBg}).click(function (){
		 		//_button.abc(dialog);
//		 		this.a(dialog);
options.testEvent(i);
		 		/*coverLayer.remove();
		  	    dialog.remove();*/
		 	}).appendTo(buttonContent)
		 })
		}
		//判断是否显示关闭图片	
		if(options.closeImgUrl!=""&&options.closeImgUrl!=undefined){
		  var closeImg=$('<img>').addClass('close-icon').attr('src',options.closeImgUrl).appendTo(dialog).click(function (){
		  	coverLayer.remove();
		  	dialog.remove();
		  });
		}
		 return dialog;
	}
})(jQuery)
