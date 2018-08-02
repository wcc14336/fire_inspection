/**
 * alert 工具类
 * confirm方法使用后必须使用message方法才能去除
 * @author c1700092
 * 使用页面引入 
	<script src="js/utils/alertUtils.js"></script>
    <script src="bootstrap/plugins/sweetalert/dist/sweetalert.min.js"></script>
 */
var alertUtils = {
	/**
	 * 确认框
	 * type:提示类型
	 * 		success:成功提示
	 * 		warning:警告提示
	 * tips：确认框提示信息
	 * callBackFn：点击确认时的回调方法
	 * 		例：alertUtils.confirm("确认删除吗", deleteInfo) deleteInfo为回调函数方法名，当回调方法中代码不是很多时使用：
	 * 		   alertUtils.confirm("确认删除吗", function(){
	 * 		   	  TODO 逻辑代码
	 * 		   })
	 */
	confirm : function(type, tips, callBackFn){
	    swal({   
	        title: tips,   
	        text: "该操做不可回退!",   
	        type: type, //success    warning
	        showCancelButton: true,   
	        confirmButtonColor: "#DD6B55",   
	        confirmButtonText: "确认!",   
	        closeOnConfirm: false 
	    }, 
	    function(){   
	    	callBackFn();
	    });
	},
	
	/**
	 * 信息提示框
	 * tips：提示信息
	 */
	message : function(tips){
		swal(tips);
	},
}