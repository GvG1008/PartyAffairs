function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var id=getUrlParam('id');//视频id
var navList = "";
var app = new Vue({
	el:"#app",
	data:{
		content:[],
		choice:[]
	},
	methods:{
		getradios:function(id){
			getradios(id);
		},
		getchecks:function(id){
			getchecks(id);	
		},
		getsorts:function(id){
			getsorts(id);
		},
		abandon:function(id){
			abandon(id);
		}
	},
	created:function(){
		var that = this;
		$.ajax({
			type:"get",
			url:"../voteinfo/"+id,
			async:true,
			success:function(res){
				if(res.status == 0){
					app.content = res.data.voteInfo;
					app.choice = res.data.choice;
					if(app.content.type == 2){
						navList = app.choice;
						that.$nextTick(function(){
							makeSortVote();
						})
					}
				}
			}
		});
	}
})

function makeSortVote(){
	
	
	const $ = selectors => document.querySelector(selectors);
    const $$ = selectors => document.querySelectorAll(selectors);
    const holdItemDom = document.createElement('div');
    holdItemDom.classList.add('item', 'hold');
    const listDom = document.createElement('div');
    listDom.classList.add('items');
    listDom.setAttribute("id","items")

	for(var i = 0; i < navList.length; i++){
		const itemDom = document.createElement('div');
		itemDom.classList.add('item');
		itemDom.dataset.sortindex = navList[i].choiceId;
		itemDom.textContent = navList[i].choiceContent;
		listDom.appendChild(itemDom);
	}

    $('.box').appendChild(listDom);
    //主要代码
    var dragObj;
    //考虑到item数量会很多，不需要给每个item单独绑定mousedown,mousemove,mouseup事件
    listDom.onmousedown = (event) => {
      let currenIndex, previousIndex;
      const itemDomList = $$('.items .item');
      const listLength = navList.length;
      const startY = event.clientY;
      //找到mousedown的item
      let selectIndex;
      dragObj = event.target;
      Array.from(itemDomList).forEach((v, i) => {
        if (v === event.target) {
          selectIndex = i;
        }
      });
      dragObj.after(holdItemDom);
      dragObj.classList.add('select');
      dragObj.style.top = dragObj.clientHeight * selectIndex + 'px';
      const itemHeight = dragObj.clientHeight;
      let topIndex = selectIndex;
      const startTop = dragObj.style.top;
      
      previousIndex = Math.ceil((parseInt(startTop) - itemHeight / 2) / itemHeight);
      document.onmousemove = function (event) {
        dragObj.classList.add('select');
        const moveY = event.clientY;
        const presentTop = parseInt(startTop) + (moveY - startY);
        dragObj.style.top = presentTop + 'px';
        currenIndex = Math.ceil((presentTop - itemHeight / 2) / itemHeight);
        if (currenIndex < 0) currenIndex = 0;
        if (currenIndex > listLength - 1) currenIndex = listLength - 1;
        if (previousIndex !== currenIndex) {
          holdItemDom.remove();
          if (previousIndex > currenIndex && currenIndex < topIndex) {
            dragObj.remove();
            itemDomList[currenIndex].before(dragObj);
            dragObj.after(holdItemDom);
          } else {
            const currentItems = $$('.box .item');
            currentItems[currenIndex].after(holdItemDom);
          }
          previousIndex = currenIndex;
          if (currenIndex < topIndex) topIndex = currenIndex;
        }
      };
      document.onmouseup = function (event) {
        const currentItems = $$('.box .item');
        if (currenIndex > topIndex) {
          currentItems[currenIndex].after(dragObj);
        }
        holdItemDom.remove();
        dragObj.classList.remove('select');
        dragObj.removeAttribute('style');
        document.onmousemove = null;
        document.onmouseup = null;
      }
    };
	
	
}

function getradios(id){
	var radiovalue = $("input[type='radio']:checked").val();
	console.log("id:"+id+"值为："+radiovalue)
	sure(id,radiovalue);
}

function getchecks(voteId){
	var id = document.getElementsByName('duoxuan');
    var checksvalue = new Array();
    for(var i = 0; i < id.length; i++){
     if(id[i].checked)
     checksvalue.push(id[i].value);
    }
	console.log("id:"+voteId+"值为："+checksvalue)
	sure(voteId,checksvalue);
}

function getsorts(id){
	console.log("id:"+id)
	
	var myDiv = document.getElementById("items");
	var temp = new Array();
	var tempDivs = myDiv.getElementsByTagName("div");
	for(var i = 0; i < tempDivs.length; i++){
		
		temp.push(tempDivs[i].dataset.sortindex)
	}
	console.log(temp)
	sure(id,temp)
}

function abandon(id){
	console.log("弃票id为"+id+"投票")
	$.ajax({
		type:"post",
		url:"../voteresult/"+id+"/"+1,
		async:true,
		success:function(res){
			//console.log(res)
			if(res.status == 0){
				alert("提交完毕")
				location.href="home.html?location=myvote"
			}
		}
	});
}

function sure(id,choice){
	$.ajax({
		type:"post",
		data:JSON.stringify(choice),
		contentType:"application/json",
		url:"../voteresult/"+id+"/"+0,
		async:true,
		success:function(res){
			//console.log(res)
			if(res.status == 0){
				alert("提交完毕")
				location.href="home.html?location=myvote"
			}
		}
	});
}
