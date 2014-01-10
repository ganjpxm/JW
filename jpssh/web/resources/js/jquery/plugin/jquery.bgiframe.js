/*! Copyright (c) 2010 Brandon Aaron (http://brandonaaron.net)
 * Licensed under the MIT License (LICENSE.txt).
 *
 * Version 2.1.3-pre
 */

(function($){

$.fn.bgiframe = ($.browser.msie && /msie 6\.0/i.test(navigator.userAgent) ? function(s) {
    s = $.extend({
        top     : 'auto', // auto == .currentStyle.borderTopWidth
        left    : 'auto', // auto == .currentStyle.borderLeftWidth
        width   : 'auto', // auto == offsetWidth
        height  : 'auto', // auto == offsetHeight
        opacity : true,
        src     : 'javascript:false;'
    }, s);
    var html = '<iframe class="bgiframe"frameborder="0"tabindex="-1"src="'+s.src+'"'+
                   'style="display:block;position:absolute;z-index:-1;'+
                       (s.opacity !== false?'filter:Alpha(Opacity=\'0\');':'')+
                       'top:'+(s.top=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderTopWidth)||0)*-1)+\'px\')':prop(s.top))+';'+
                       'left:'+(s.left=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderLeftWidth)||0)*-1)+\'px\')':prop(s.left))+';'+
                       'width:'+(s.width=='auto'?'expression(this.parentNode.offsetWidth+\'px\')':prop(s.width))+';'+
                       'height:'+(s.height=='auto'?'expression(this.parentNode.offsetHeight+\'px\')':prop(s.height))+';'+
                '"/>';
    return this.each(function() {
        if ( $(this).children('iframe.bgiframe').length === 0 )
            this.insertBefore( document.createElement(html), this.firstChild );
    });
} : function() { return this; });

// old alias
$.fn.bgIframe = $.fn.bgiframe;

function prop(n) {
    return n && n.constructor === Number ? n + 'px' : n;
}

})(jQuery);

/*
* ol.loading plugin
* Version 1.1 (12/7/2010)
* @requires jQuery v1.2.6 or later
*
* Example at: http://www.open-lib.com/Lib/1066.jsp
*
* Copyright (c) 2009-2010 Open-Lib.Com
* Dual licensed under the MIT and GPL licenses:
* http://www.opensource.org/licenses/mit-license.php
* http://www.gnu.org/licenses/gpl.html
*
* Read the related post and contact the author at http://www.open-lib.com/Lib/1066.jsp
*
* This version is far from perfect and doesn't manage it's own state, therefore contributions are more than welcome!
*
* Usage: var loading=new ol.loading({id:"table1"});
*		loading.show();
*		loading.hide();
*
* Tested in IE6 IE7 Firefox. Any browser strangeness, please report.
*/
if (!window['ol']) {
	window['ol'] = {};
}
(function() {
	var $ = jQuery;
	ol.loading=function(options) {
		var self=this;
		this.loadingImg;
		this.loadingMask;
		this.container;

		var _defaults= {
			id:null,
			loadingClass:null,
			zIndex:800
		};


		this.init=function(){
			options  = $.extend({},_defaults, options);
			this.container=$("#"+options.id);
			var position=this.container.css("position");
			var width=this.container.outerWidth();
			position=position=="absolute"?"absolute":"relative"
			width=width>0?width:"";
			var f=$("<div></div>").css({
				position:position,
				top:this.container.css("top"),
				left:this.container.css("left"),
				right:this.container.css("right"),
				bottom:this.container.css("bottom"),
				width:width
			});
			this.container.css({
				position:"relative",
				width:"100%",
				top:null,
				right:null,
				left:null,
				bottom:null
			}).wrap(f);

			this.loadingMask=$('<div class="ol_loading_mask"></div>');
			this.loadingMask.css({
				zIndex: options.zIndex
			});
			this.loadingImg=$('<div class="ol_loading"></div>').css("z-index",options.zIndex+1);
			if(!options.loadingClass)
			{
				this.loadingImg.addClass(options.loadingClass);
				this.loadingMask.addClass(options.loadingClass+"_mask");
			}

			this.container.parent().append(this.loadingMask).append(this.loadingImg);
			this.loadingMask.bgiframe();
		}

		this.show=function()
		{
			if ($.browser.msie && /6.0/.test(navigator.userAgent)) {
				this.loadingMask.css({
					width:this.container.outerWidth(),
					height:this.container.outerHeight()
				});
			}
			this.loadingMask.css("display", "block");
			this.loadingImg.css("display", "block");
		}
		this.hide=function()
		{
			this.loadingMask.css("display", "none");
			this.loadingImg.fadeOut(0);
		}
		this.init();
	};
})();