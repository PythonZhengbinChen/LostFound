/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: CN
 */
jQuery.extend(jQuery.validator.messages, {
        required: "此处必填",
		remote: "请修正该字段",
		email: "邮箱不对",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.validator.format("太长啦"),
		minlength: jQuery.validator.format("太短了哦"),
		rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min: jQuery.validator.format("请输入一个最小为 {0} 的值"),

        notnull:"不能为空"
});

/*扩展验证规则 jQuery Validation */

// 邮政编码验证   
jQuery.validator.addMethod("zipcode", function (value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "邮政编码不正确");

// 手机号码验证   
jQuery.validator.addMethod("mobile", function (value, element) {
    var tel = /^1[3|4|5|8][0-9]\d{4,8}$/;
    return this.optional(element) || (tel.test(value));
}, "手机号码不正确");

// QQ号验证   
jQuery.validator.addMethod("qq", function (value, element) {
    var tel = /^[1-9]\d{4,10}$/;
    return this.optional(element) || (tel.test(value));
}, "QQ号码不正确");

// 中文姓名验证   
jQuery.validator.addMethod("CHS", function (value, element) {
    var tel = /^[\u0391-\uFFE5]{2,4}$/;
    return this.optional(element) || (tel.test(value));
}, "姓名（最多4个汉字）");

// fdate   
jQuery.validator.addMethod("fdate", function (value, element) {
    //var tel = /^((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$))$/;
    var tel = /^([1-9]\d{3})-((1[012])|(0[1-9]))-(([12][012])|(0[1-9])|(3[01]))$/;
    return this.optional(element) || (tel.test(value));
}, "时间格式不对,如2013-02-01");