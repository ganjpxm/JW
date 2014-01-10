/**
 *@class I18N
 *description:js multi language class,should be loaded before others,singleton.
 *author:ganjp
 *date:2010-8-16
 */
var I18N = null;
if(!I18N){
I18N = {
	save: "保存",
	reset: "重置",
	close: "关闭",
	confirm: "确定",
	cancel: "取消",
	yes: "是",
	no: "否",
	refresh: "刷新",
	loading: "请稍等...",
	processing: "处理中...",
	choose: "请选择...",
	promp: "提示",
	add: "增加",
	edit: "编辑",
	del: "删除",
	search: "查询",
	view: "查看",
	
	msg_save_sucess: "保存成功",
	msg_save_fail: "保存失败",
	msg_del_sucess: "删除成功",
	msg_del_confirm: "您确定要删除所选的记录吗?",
	msg_no_sel: "请先选择",
	msg_single_edit: "编辑时只能单选!",
	msg_fg_verify_fail_tip: "有非法字符不能保存!",
	msg_saving: "正在保存。。。",
	msg_system_error: "系统错误",
	msg_set_form_id: "请在表单中设置ID项:",
	msg_pwd_not_match: "密码不一致",
	msg_cd_format_error: "该输入项只能包含半角字母,数字,-和_",
	msg_chinese_format_error: "该项只能输入中文",
	msg_no_image_del: "没有图片，不用删除",
	msg_sign_up_success: "注册成功",
	msg_sign_up_fail: "注册失败",
	msg_exist: "已经存在",
	msg_error_user_cd_password: "账号与密码不匹配 ,请您再试一下",
	msg_delete_link_title: "删除所选的连接?",
	msg_delete_article_title: "删除所选的文章 ?",
	msg_delete_photo_title: "删除所选的图片 ?",
	msg_delete_video_title: "删除所选的视频?",
	msg_delete_content: "您是否要删除 {0}?",
	
	zeroRecords: "没有匹配的数据",
	emptyTable: "数据为空",
	displayRecords: "显示 {0} - {1} 条, 共 {2} 条",
	infoFiltered: "总共查找到 {0} 条",
	firstPage: "首页",
	previousPage: "上一页",
	nextPage: "下一页",
	lastPage: "最后页",
	view: "查看",
	pageSize: "30",
	
	uuid_get_fail: "获取uuid失败"
};
};
