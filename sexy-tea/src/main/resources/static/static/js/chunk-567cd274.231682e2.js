(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-567cd274"],{1184:function(e,t,i){},"35e6":function(e,t,i){"use strict";var a=i("1184"),s=i.n(a);s.a},"386d":function(e,t,i){"use strict";var a=i("cb7c"),s=i("83a1"),o=i("5f1b");i("214f")("search",1,function(e,t,i,n){return[function(i){var a=e(this),s=void 0==i?void 0:i[t];return void 0!==s?s.call(i,a):new RegExp(i)[t](String(a))},function(e){var t=n(i,e,this);if(t.done)return t.value;var l=a(e),r=String(this),c=l.lastIndex;s(c,0)||(l.lastIndex=0);var u=o(l,r);return s(l.lastIndex,c)||(l.lastIndex=c),null===u?-1:u.index}]})},"83a1":function(e,t){e.exports=Object.is||function(e,t){return e===t?0!==e||1/e===1/t:e!=e&&t!=t}},ac79:function(e,t,i){"use strict";i.r(t);var a=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("section",[i("div",{staticClass:"app-container"},[i("div",{staticStyle:{display:"inline-flex"}},[i("el-input",{staticClass:"search",attrs:{size:"mini",placeholder:"输入关键字搜索"},on:{input:function(t){return e.searchfuc()},focus:function(t){return e.inputChange()}},model:{value:e.search,callback:function(t){e.search=t},expression:"search"}}),e._v(" "),i("el-select",{staticClass:"xialakuan",attrs:{placeholder:"默认名称查询",size:"mini"},on:{change:e.changeValue},model:{value:e.value,callback:function(t){e.value=t},expression:"value"}},e._l(e.options,function(e){return i("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1),e._v(" "),i("el-button",{staticClass:"xialakuan",attrs:{size:"mini"},on:{click:function(t){return e.addgoods()}}},[e._v("\n        添加饮品\n        "),i("i",{staticClass:"el-icon-plus"})])],1),e._v(" "),i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{data:e.list,"element-loading-text":"加载中",fit:"","highlight-current-row":""}},[i("el-table-column",{attrs:{label:"饮品id",align:"center",prop:"id"}}),e._v(" "),i("el-table-column",{attrs:{label:"饮品名称",align:"center",prop:"foodName"}}),e._v(" "),i("el-table-column",{attrs:{label:"饮品图片",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("img",{staticStyle:{width:"80px",higth:"80px"},attrs:{src:e.imgUrl+t.row.foodImage,"preview-src-list":e.imgUrl+t.row.foodImage}})]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"饮品价格",align:"center",prop:"price"}}),e._v(" "),i("el-table-column",{attrs:{prop:"status",label:"饮品状态",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-tag",{attrs:{type:1!=t.row.status?"primary":"success","disable-transitions":""}},[e._v(e._s(e.getorderStatus(t.row.status)))])]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"更新时间",align:"center",prop:"updateTime"}}),e._v(" "),i("el-table-column",{attrs:{label:"操作",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{size:"mini",type:"primary",icon:"el-icon-edit",circle:""},on:{click:function(i){return e.handleEdit(t.$index,t.row)}}}),e._v(" "),i("el-button",{attrs:{size:"mini",type:"danger",icon:"el-icon-delete",circle:""},on:{click:function(i){return e.handleDelete(t.$index,t.row)}}})]}}])})],1),e._v(" "),i("el-pagination",{staticClass:"pagination",attrs:{align:"center","current-page":e.currentPage,"page-sizes":[5,10,20,30,40],"page-size":e.pageSize,background:"",layout:"total, sizes, prev, pager, next, jumper",total:e.totalNum},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1),e._v(" "),i("el-dialog",{attrs:{title:"添加饮品",visible:e.dialogVisible,width:"45%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[i("el-form",{ref:"form",attrs:{rules:"rules",model:e.form,"label-width":"80px",rules:e.rules}},[i("el-form-item",{attrs:{label:"饮品名称",prop:"foodName"}},[i("el-input",{model:{value:e.form.foodName,callback:function(t){e.$set(e.form,"foodName",t)},expression:"form.foodName"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"饮品价格",prop:"price"}},[i("el-input",{model:{value:e.form.price,callback:function(t){e.$set(e.form,"price",t)},expression:"form.price"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"饮品图片",prop:"foodImage"}},[i("el-upload",{attrs:{"on-exceed":e.handleExceed,limit:1,"on-success":e.handleAvatarSuccess,action:e.dev_url,"list-type":"picture-card","on-remove":e.beforeRemove}},[i("i",{staticClass:"el-icon-plus"})])],1),e._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.addNewGoods("form")}}},[e._v("立即发布")]),e._v(" "),i("el-button",{on:{click:function(t){return e.resetForm("form")}}},[e._v("重置")]),e._v(" "),i("el-button",{on:{click:function(t){e.dialogVisible=!1,e.resetForm("form")}}},[e._v("取消")])],1)],1)],1),e._v(" "),i("el-dialog",{attrs:{title:"修改饮品",visible:e.editVisible,width:"45%","show-close":!1},on:{"update:visible":function(t){e.editVisible=t}}},[i("el-form",{attrs:{model:e.editform,"label-width":"80px"}},[i("el-form-item",{attrs:{label:"饮品名称"}},[i("el-input",{model:{value:e.editform.foodName,callback:function(t){e.$set(e.editform,"foodName",t)},expression:"editform.foodName"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"饮品价格"}},[i("el-input",{model:{value:e.editform.price,callback:function(t){e.$set(e.editform,"price",t)},expression:"editform.price"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"饮品状态"}},[i("el-select",{attrs:{placeholder:"请选择"},model:{value:e.editform.status,callback:function(t){e.$set(e.editform,"status",t)},expression:"editform.status"}},e._l(e.statusOptions,function(e){return i("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1),e._v(" "),i("el-form-item",{attrs:{label:"饮品图片"}},[i("el-upload",{staticClass:"upload-demo",attrs:{action:e.dev_url,"on-success":e.imgSuccess,"on-exceed":e.handleExceed,"file-list":e.fileList,limit:2,"list-type":"picture"}},[i("el-button",{attrs:{size:"small",type:"primary"}},[e._v("更改图片")])],1)],1),e._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.editGoods()}}},[e._v("立即发布")]),e._v(" "),i("el-button",{on:{click:function(t){e.editVisible=!1,e.getCommodity()}}},[e._v("取消")])],1)],1)],1)],1)},s=[],o=(i("386d"),i("e05f"),{name:"Editor",data:function(){return{fileList:[],rules:{foodName:[{required:!0,message:"请输入名称",trigger:"blur"}],foodImage:[{required:!0,message:"请选择图片",trigger:"blur"}],price:[{required:!0,message:"请输入价格",trigger:"blur"}]},value:"1",options:[{value:"2",label:"根据id查询"},{value:"1",label:"根据名称查询"}],statusOptions:[{value:"1",label:"在售"},{value:"0",label:"下架"}],multipleSelection:[],programForm:[{selectedOptions:{}}],navId:"1",list:[],listLoading:!0,search:"",currentPage:1,pageSize:5,totalNum:0,addCategoryname:"",addCategoryid:"",editDialogVisible:!1,form:{},dialogVisible:!1,editform:{},editVisible:!1,imgUrl:"",dev_url:""}},mounted:function(){this.imgUrl=this.GLOBAL.BASE_URL,this.dev_url=this.GLOBAL.dev_URL+"/beverage/upload",this.getCommodity()},methods:{inputChange:function(){this.search.length>0?(this.currentPage=1,this.pageSize=5):this.getCommodity()},searchfuc:function(){var e=this;this.search.length>0?(this.listLoading=!0,"1"==this.value?this.$api.foods.itemsByName({name:this.search,pageNum:this.currentPage,pageSize:this.pageSize}).then(function(t){e.list=t.data.data.result,e.totalNum=t.data.data.total,e.listLoading=!1}).catch(function(e){}):this.$api.foods.itemsById(this.search).then(function(t){e.list=[t.data.data],e.totalNum=1,e.pageSize=5,e.listLoading=!1}).catch(function(e){})):(this.currentPage=1,this.pageSize=5,this.getCommodity())},changeValue:function(){this.search="",this.searchfuc()},getCommodity:function(){var e=this;this.listLoading=!0,this.search="",this.$api.foods.items({pageNum:this.currentPage,pageSize:this.pageSize}).then(function(t){console.log(t),e.list=t.data.data.result,e.totalNum=t.data.data.total,e.listLoading=!1}).catch(function(e){})},handleExceed:function(e,t){this.$message.warning("当前限制选择 1 张图片")},beforeRemove:function(e,t){},handleAvatarSuccess:function(e,t,i){this.form.foodImage=e.data},imgSuccess:function(e,t,i){this.editform.foodImage=e.data,this.fileList=[{name:e.data,url:this.imgUrl+e.data}],console.log(e)},addgoods:function(){this.form={foodName:"",foodImage:"",price:""},this.dialogVisible=!0},addNewGoods:function(e){var t=this;this.$refs[e].validate(function(e){if(e){var i=t.form;console.log(i),t.$api.foods.save(i).then(function(e){console.log(e),t.dialogVisible=!1}).catch(function(e){})}else t.$message({showClose:!0,message:"添加不能为空！",type:"error"})})},resetForm:function(e){this.$refs[e].resetFields()},handleClick:function(e,t){this.getCommodity(),this.search=""},getorderStatus:function(e){return 1==e?(e="在售",e):0==e?(e="下架",e):-1==e?(e="已删除",e):void 0},getorderType:function(e){return 1==e?(e="奶茶",e):2==e?(e="咖啡",e):void 0},handleEdit:function(e,t){if(-1==t.status)this.$message({message:"警告哦，商品已删除不可编辑",type:"warning"});else{this.fileList=[{name:t.foodImage,url:this.imgUrl+t.foodImage}],console.log(e,t);var i=t;this.editform=i,1==t.status&&(this.editform.status="在售"),0==t.status&&(this.editform.status="下架"),-1==t.status&&(this.editform.status="已删除"),console.log("sssssssssss",t.type),1===t.type&&(this.editform.type="奶茶"),2===t.type&&(this.editform.type="咖啡"),this.editVisible=!0}},handleDelete:function(e,t){var i=this;this.$confirm("确认删除？").then(function(e){i.listLoading=!0,i.$api.foods.delete(t.id).then(function(e){console.log(e),i.$message({message:"删除成功！",type:"success"}),i.getCommodity(),that.listLoading=!1}).catch(function(e){})}).catch(function(e){})},handleSizeChange:function(e){this.pageSize=e,this.search.length>0?this.searchfuc():this.getCommodity()},handleCurrentChange:function(e){this.currentPage=e,this.search.length>0?this.searchfuc():this.getCommodity()},editGoods:function(){var e=this;"在售"==this.editform.status&&(this.editform.status=1),"下架"==this.editform.status&&(this.editform.status=0),"奶茶"==this.editform.type&&(this.editform.type=1),"咖啡"==this.editform.type&&(this.editform.type=2),this.$api.foods.update(this.editform).then(function(t){console.log(t),e.$message({message:"修改成功！",type:"success"}),e.getCommodity(),e.editVisible=!1}).catch(function(e){})}}}),n=o,l=(i("35e6"),i("2877")),r=Object(l["a"])(n,a,s,!1,null,"00ab9f50",null);t["default"]=r.exports}}]);