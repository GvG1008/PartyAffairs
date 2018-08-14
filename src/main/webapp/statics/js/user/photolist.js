/**
 * 活动相册列表
 */
var ftpurl = "http://172.21.95.5:19091/";
var selected1 = new Vue({
	el : '#selected1',
	data :{
		datas:[]
	},
	created	: function(){
		var self = this;
		$.ajax({
			type : "GET",// 请求方式
			url : "../partyalbum/1",// 地址，就是json文件的请求路径
			dataType : "json",// 数据类型可以为 text xml json script jsonp
			success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
				if (result.status == 0) {
					self.datas = result.data;
				} else {
					alert(result.msg);
				}
			}
		});
	},
	methods:{
		getImageUrl:function(url){
			return ftpurl+url;
		}
	}
})
var selected2 = new Vue({
	el : '#selected2',
	data :{
		datas:[]
	},
	created	: function(){
		var self = this;
		$.ajax({
			type : "GET",// 请求方式
			url : "../partyalbum/2",// 地址，就是json文件的请求路径
			dataType : "json",// 数据类型可以为 text xml json script jsonp
			success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
				if (result.status == 0) {
					self.datas = result.data;
				} else {
					alert(result.msg);
				}
			}
		});
	},
	methods:{
		getImageUrl:function(url){
			return ftpurl+url;
		}
	}
})
var selected3 = new Vue({
	el : '#selected3',
	data :{
		datas:[]
	},
	created	: function(){
		var self = this;
		$.ajax({
			type : "GET",// 请求方式
			url : "../partyalbum/3",// 地址，就是json文件的请求路径
			dataType : "json",// 数据类型可以为 text xml json script jsonp
			success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
				if (result.status == 0) {
					self.datas = result.data;
				} else {
					alert(result.msg);
				}
			}
		});
	},
	methods:{
		getImageUrl:function(url){
			return ftpurl+url;
		}
	}
})
var selected4 = new Vue({
	el : '#selected4',
	data :{
		datas:[]
	},
	created	: function(){
		var self = this;
		$.ajax({
			type : "GET",// 请求方式
			url : "../partyalbum/4",// 地址，就是json文件的请求路径
			dataType : "json",// 数据类型可以为 text xml json script jsonp
			success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
				if (result.status == 0) {
					self.datas = result.data;
				} else {
					alert(result.msg);
				}
			}
		});
	},
	methods:{
		getImageUrl:function(url){
			return ftpurl+url;
		}
	}
})
var selected5 = new Vue({
	el : '#selected5',
	data :{
		datas:[]
	},
	created	: function(){
		var self = this;
		$.ajax({
			type : "GET",// 请求方式
			url : "../partyalbum/5",// 地址，就是json文件的请求路径
			dataType : "json",// 数据类型可以为 text xml json script jsonp
			success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
				if (result.status == 0) {
					self.datas = result.data;
				} else {
					alert(result.msg);
				}
			}
		});
	},
	methods:{
		getImageUrl:function(url){
			return ftpurl+url;
		}
	}
})
