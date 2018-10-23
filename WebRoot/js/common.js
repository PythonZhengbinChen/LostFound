//设置搜索框
$(function () {
    var search = $("#searchtext");
    var text = search.val();
    search.focus(function () {
        search.val("");
    });

    search.blur(function () {
        search.val(text);
    });
});

/*form 初始化表单 并验证提交*/
function form(obj, rules,url, type, msg,btn,callback) {
    //$.metadata.setType("attr", "validate");
    var container = $(msg);

    //构造表单参数
    var options = {
        success: function (data) {
            if (data != null) {
                if (data.success) {
                    if (callback) {
                        callback(container);
                    } else {
                        if (data.message) {
                            container.html(data.message).show();
                        } else {
                            container.html("提交成功啦!").show();
                        }
                    }
                } else {
                    var msg = "";
                    if (data.message) {
                        msg = "PS："+data.message;
                    } 
                    container.html("很抱歉，没有提交成功！请检查后重新提交！"+msg).show();
                    //释放按钮
                    $(btn).removeAttr("disabled");
                }
            } else {
                container.html("服务器没理你，请稍后重新尝试！").show();
                //释放按钮
                $(btn).removeAttr("disabled");
            }
        }, // post-submit callback
        error:function(){
            container.html("系统出现异常，请重新尝试！").show();
            //释放按钮
            $(btn).removeAttr("disabled");
        },
        type: type,
        url: url,
        dataType: 'json',
        // other available options: 
        //url:       url         // override for form's 'action' attribute 
        //type:      type        // 'get' or 'post', override for form's 'method' attribute 
        //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
        //clearForm: true        // clear all form fields after successful submit 
        resetForm: false      // reset the form after successful submit

        // $.ajax options can be used here too, for example: 
        //timeout:   3000 
    };

    var v = $(obj).validate({
        rules:rules,
        //调试状态，不会提交数据的
        debug: false,
        errorPlacement: function (lable, element) {
            if (element.hasClass("l-textarea")) {
                element.addClass("l-textarea-invalid");
            }
            else if (element.hasClass("l-text-field")) {
                element.parent().addClass("l-text-invalid");
            }

            var nextCell = element.nextAll('span').first();
            //var nextCell = element.parents("dd:first").next("dd");
            var tip = nextCell.find("label.u-tip");
            if (tip.length == 0) {
                $('<label class="u-tip alert " >' + lable.html() + '</label>').appendTo(nextCell);
            } else {
                tip.remove();
                $('<label class="u-tip alert" >' + lable.html() + '</label>').appendTo(nextCell);
                // tip.ligerTip({ content: lable.html() });
            }
        },

        invalidHandler: function (form, validator) {
            var errors = validator.numberOfInvalids();
            if (errors) {
                var message = '注意：有 ' + errors + ' 项不符合要求，已经标出，请改正后重新提交. ';
                container.html(message).show();
            }
        },
        success: function (lable) {
            //var element = $("#" + lable.attr("for"));
            var element = $('[name="'+lable.attr("for")+'"]');
            var nextCell = element.nextAll('span').first();
            if (element.hasClass("l-textarea")) {
                element.removeClass("l-textarea-invalid");
            }
            else if (element.hasClass("l-text-field")) {
                element.parent().removeClass("l-text-invalid");
            }
            //alert(lable.attr("for"));
            nextCell.find("label.u-tip").remove();
            $('<label class="u-tip suc" >Ok</label>').appendTo(nextCell);
        },
        submitHandler: function (form) {
            container.html("正在提交....").show();
            //锁定按钮
           $(btn).attr("disabled", "disabled");
           $(obj).ajaxSubmit(options);
        }
    });
}

//异步ajax请求
function ajaxAction(url, type, dat, timeout, callback) {
    $.ajax({
        url: url,
        type: type,
        data: dat,
        cache: false,
        dataType: "json",
        timeout: timeout,
        error: function () {
            //$.ligerDialog.error('系统出现异常，请重新尝试！');
        },
        success: function (data) {
            if (data != null) {
                if (data.success) {
                    if (callback) {
                        //$.ligerDialog.success(data.message,"提示",callback);
                    } else {
                        //$.ligerDialog.success(data.message);
                    }            
                } else {
                    //$.ligerDialog.error('操作失败，请重新尝试！');
                }
            } else {
                //$.ligerDialog.error('操作错误，请重新尝试！');
            }
        }
    });
}

//提示框
(function ($) {
    $.fn.avgrund = function (options) {
        var defaults = {
            width: 640, // max = 640
            height: 350, // max = 350
            showClose: false,
            showCloseText: '',
            closeByEscape: true,
            closeByDocument: true,
            overlayClass: '',
            openOnEvent: true,
            setEvent: 'click',
            onLoad: function () { },
            onUnload: function () { },
            target: ''
        };

        options = $.extend(defaults, options);

        return this.each(function () {
            var self = $(this),
				body = $('body'),
				maxWidth = options.width > 640 ? 640 : options.width,
				maxHeight = options.height > 350 ? 350 : options.height,
				template = typeof options.template === 'function' ? options.template(self) : options.template;

            // close popup by clicking Esc button
            function onDocumentKeyup(e) {
                if (options.closeByEscape) {
                    if (e.keyCode === 27) {
                        deactivate();
                    }
                }
            }

            // close popup by clicking outside it
            function onDocumentClick(e) {
                e.preventDefault();

                if (options.closeByDocument) {
                    if ($(e.target).is('.m-avgrund-overlay, .m-avgrund-close')) {
                        deactivate();
                    }
                } else {
                    if ($(e.target).is('.m-avgrund-close')) {
                        deactivate();
                    }
                }
            }

            // show popup
            function activate() {
                // check if onLoad is a function and call it before popin is active
                if (typeof options.onLoad === 'function') {
                    options.onLoad.call(self);
                }

                var pop = $(options.target);
                //alert(options.target);
                //var pop = $('<div class="avgrund-popin ' + options.holderClass + '">' + template + '</div>');

                pop.addClass("m-avgrund-popin");
              
                //body.append(pop);

                //pop.css({
                ///   'width': maxWidth + 'px',
                 //   'height': maxHeight + 'px',
                 //  'margin-left': '-' + (maxWidth / 2 + 10) + 'px',
                  //  'margin-top': '-' + (maxHeight / 2 + 10) + 'px'
                //});

                pop.css({
                   'width': maxWidth + 'px',
                    'height': maxHeight + 'px',
                    'left': ($(window).width()-maxWidth) / 2  + 'px',
                    'top': ($(document).height()-maxHeight) / 2 + 'px'
                });

                if (options.showClose) {
                    pop.append('<a href="#" class="m-avgrund-close">' + options.showCloseText + '</a>');
                }

                // fixing -webkit overlay 'transform/position:fixed/overflow' issue
                //body.wrapInner('<div class="avgrund-wrap-inner" />');

                body.bind('keyup', onDocumentKeyup);
                body.bind('click', onDocumentClick);

                var overlay = $('<div class="m-avgrund-overlay ' + options.overlayClass + '"></div>')
                overlay.css({
                    'height': $(document).height() + 'px'
                });
                body.append(overlay);
                pop.show();
            }

            // hide popup
            function deactivate() {
                body.unbind('keyup', onDocumentKeyup);
                body.unbind('click', onDocumentClick);

                // prevent multiple overlays
                $('.m-avgrund-overlay').remove();

                setTimeout(function () {
                    $('.m-avgrund-popin').hide();
                }, 50);

                // check if onUnload is a function and call it after popin is closed
                if (typeof options.onUnload === 'function') {
                    options.onUnload.call(self);
                }
            }

            // init on click or custom event
            if (options.openOnEvent) {
                self.bind(options.setEvent, function (e) {
                    e.stopPropagation();
                    // prevent redirect for href url
                    if ($(e.target).is('a')) {
                        e.preventDefault();
                    }

                    activate();
                });
            } else {
                activate();
            }
        });

    };
})(jQuery);



