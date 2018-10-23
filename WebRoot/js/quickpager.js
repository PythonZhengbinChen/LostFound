(function ($) {

    $.fn.pager = function (options) {

        var defaults = {
            pageSize: 10,
            currentPage: 1,
            total: 201,
            url: ''
        };

        var options = $.extend(defaults, options);

        return this.each(function () {

            var selector = $(this);
            var pageCounter = 1;

            if (options.total) {
                pageCounter = Math.ceil(options.total / options.pageSize);
            }

            /*
                <div class="m-page m-page-sr m-page-sm">
                        <a href="#" class="first pageprv z-dis"><span class="pagearr">&lt;</span>涓婁竴椤�</a>
                        <a href="#">1</a>
                        <a href="#" class="z-crt">2</a>
                        <a href="#">3</a>
                        <a href="#">4</a>
                        <i>...</i>
                        <a href="#">10</a>
                        <a href="#" class="last pagenxt">涓嬩竴椤�<span class="pagearr">&gt;</span></a>
                </div>
            */

            //Build pager navigation
            //var pageNav = "<div class='m-page m-page-sr m-page-sm'>";

            var pageNav = "";
            //alert(pageCounter);

            if (pageCounter>1&&pageCounter <=10) {

                if(options.currentPage==1){                           
                         pageNav += "<a href='javascript:void(0)' class='first pageprv z-dis'><span class='pagearr'>&lt;</span>上一页</a>";
                    }else{
                         pageNav += "<a href='" + options.url + (options.currentPage-1) + "' class='first pageprv'><span class='pagearr'>&lt;</span>上一页</a>";
                }

                for (i = 1; i <= pageCounter; i++) {
                    if (i == options.currentPage) {
                        pageNav += "<a class='z-crt' rel='" + i + "' href='" + options.url + i + "'>" + i + "</a>";
                    }
                    else {
                        pageNav += "<a rel='" + i + "' href='" + options.url + i + "'>" + i + "</a>";
                    }
                }

                if(options.currentPage!=pageCounter){
                        pageNav += "<a href='" + options.url + (options.currentPage+1) + "' class='last pagenxt'>下一页<span class='pagearr'>&gt;</span></a>";
                    }else{
                        pageNav += "<a href='javascript:void(0)' class='last pagenxt z-dis'>下一页<span class='pagearr'>&gt;</span></a>";
                }
            }

            if (pageCounter >10) {

                if(options.currentPage<=5){

                    if(options.currentPage==1){                           
                         pageNav += "<a href='javascript:void(0)' class='first pageprv z-dis'><span class='pagearr'>&lt;</span>上一页</a>";
                    }else{
                         pageNav += "<a href='" + options.url + (options.currentPage-1) + "' class='first pageprv'><span class='pagearr'>&lt;</span>上一页</a>";
                    }

                    for(i=1;i<=options.currentPage+3;i++){
                        if (i == options.currentPage) {
                             pageNav += "<a class='z-crt' rel='" + i + "' href='" + options.url + i + "'>" + i + "</a>";
                        }else{
                            pageNav += "<a rel='" + i + "' href='" + options.url + i + "'>" + i + "</a>";
                        }   
                    }

                    pageNav += "<i>" + "..." + "</i>";
                    pageNav += "<a rel='" + pageCounter + "' href='" + options.url + pageCounter + "'>" + pageCounter + "</a>";
                    pageNav += "<a href='" + options.url + (options.currentPage+1) + "' class='last pagenxt'>下一页<span class='pagearr'>&gt;</span></a>";
                }

                if(options.currentPage>5&&pageCounter-options.currentPage>=5){

                    pageNav += "<a href='" + options.url + (options.currentPage-1) + "' class='first pageprv'><span class='pagearr'>&lt;</span>上一页</a>";

                    pageNav += "<a rel='" + 1 + "' href='" + options.url + 1 + "'>" + 1 + "</a>"; 
                    pageNav += "<i>" + "..." + "</i>";
                    for(i=options.currentPage-3;i<=options.currentPage+3;i++){
                        if (i == options.currentPage) {
                             pageNav += "<a class='z-crt' rel='" + i + "' href='" + options.url + i + "'>" + i + "</a>";
                        }else{
                            pageNav += "<a rel='" + i + "' href='" + options.url + i + "'>" + i + "</a>";
                        }   
                    }
                    pageNav += "<i>" + "..." + "</i>";
                    pageNav += "<a rel='" + pageCounter + "' href='" + options.url + pageCounter + "'>" + pageCounter + "</a>";
                    pageNav += "<a href='" + options.url + (options.currentPage+1) + "' class='last pagenxt'>下一页<span class='pagearr'>&gt;</span></a>";
                }


                if(options.currentPage>5&&pageCounter-options.currentPage<5){
                    pageNav += "<a href='" + options.url + (options.currentPage-1) + "' class='first pageprv'><span class='pagearr'>&lt;</span>涓婁竴椤�</a>";
                    pageNav += "<a rel='" + 1 + "' href='" + options.url + 1 + "'>" + 1 + "</a>"; 
                    pageNav += "<i>" + "..." + "</i>";
                    for(i=options.currentPage-5;i<=pageCounter;i++){
                        if (i == options.currentPage) {
                             pageNav += "<a class='z-crt' rel='" + i + "' href='" + options.url + i + "'>" + i + "</a>";
                        }else{
                            pageNav += "<a rel='" + i + "' href='" + options.url + i + "'>" + i + "</a>";
                        }   
                    }

                    if(options.currentPage!=pageCounter){
                        pageNav += "<a href='" + options.url + (options.currentPage+1) + "' class='last pagenxt'>涓嬩竴椤�<span class='pagearr'>&gt;</span></a>";
                    }else{
                        pageNav += "<a href='javascript:void(0)' class='last pagenxt z-dis'>涓嬩竴椤�<span class='pagearr'>&gt;</span></a>";
                    }
                }


            }
            //pageNav += " </div>";

            //alert(pageNav);


            selector.append(pageNav);

            //pager navigation behaviour
            //$(".m-page a").each(function (i) {
                //alert(i);
                //$(this).click(function () {

                    //grab the REL attribute 
                    //var clickedLink = $(this).attr("rel");
                    //options.currentPage = clickedLink;

                    //remove current current (!) page
                    //$(this).parent("li").parent("ul").find("li.currentPage").removeClass("currentPage");
                    //Add current page highlighting
                    //$(this).parent("li").parent("ul").find("a[rel='" + clickedLink + "']").parent("li").addClass("currentPage");

                    //return false;
                //});
            //});
        });
    }


})(jQuery);
