/*!pl SCEditor | (C) 2011-2013, Sam Clarke | sceditor.com/license */
(function(e,t,n){"use strict";e.sceditor.BBCodeParser=function(t){if(!(this instanceof e.sceditor.BBCodeParser))return new e.sceditor.BBCodeParser(t);var r,o,i,l,s,a,c,u,d,f,h,p,b,m,g,y=this,v={open:"open",content:"content",newline:"newline",close:"close"};Object.freeze&&Object.freeze(v);var x=function(e,t,n,r,o,i){var l=this;l.type=e,l.name=t,l.val=n,l.attrs=r||{},l.children=o||[],l.closing=i||null};x.prototype={clone:function(e){var t=this;return new x(t.type,t.name,t.val,t.attrs,e?t.children:[],t.closing?t.closing.clone():null)},splitAt:function(t){var n,r=this,o=0,i=r.children.length;if("number"!=typeof object&&(t=e.inArray(t,r.children)),0>t||t>i)return null;for(;i--;)i>=t?o++:i=0;return n=r.clone(),n.children=r.children.splice(t,o),n}},r=function(){y.opts=e.extend({},e.sceditor.BBCodeParser.defaults,t),y.bbcodes=e.sceditor.plugins.bbcode.bbcodes},y.tokenize=function(e){var t,n,r,i=[],l=[{type:"close",regex:/^\[\/[^\[\]]+\]/},{type:"open",regex:/^\[[^\[\]]+\]/},{type:"newline",regex:/^(\r\n|\r|\n)/},{type:"content",regex:/^([^\[\r\n]+|\[)/}];l.reverse();e:for(;e.length;){for(r=l.length;r--;)if(n=l[r].type,(t=e.match(l[r].regex))&&t[0]){i.push(o(n,t[0])),e=e.substr(t[0].length);continue e}e.length&&i.push(o(v.content,e)),e=""}return i},o=function(t,n){var r,o,l;return"open"===t&&(r=n.match(/\[([^\]\s=]+)(?:([^\]]+))?\]/))?(l=m(r[1]),r[2]&&(r[2]=e.trim(r[2]))&&(o=i(r[2]))):"close"===t&&(r=n.match(/\[\/([^\[\]]+)\]/))?l=m(r[1]):"newline"===t&&(l="#newline"),l&&("open"!==t&&"close"!==t||e.sceditor.plugins.bbcode.bbcodes[l])||(t="content",l="#"),new x(t,l,n,o)},i=function(t){var n,r=/([^\s=]+)=(?:(?:(["'])((?:\\\2|[^\2])*?)\2)|((?:.(?!\s\S+=))*.))/g,o=e.sceditor.plugins.bbcode.stripQuotes,i={};if("="===t.charAt(0)&&0>t.indexOf("=",1))i.defaultattr=o(t.substr(1));else for("="===t.charAt(0)&&(t="defaultattr"+t);n=r.exec(t);)i[m(n[1])]=o(n[3])||n[4];return i},y.parse=function(e,t){var n=l(y.tokenize(e));return y.opts.fixInvalidChildren&&d(n),y.opts.removeEmptyTags&&u(n),y.opts.fixInvalidNesting&&a(n),s(n,null,t),y.opts.removeEmptyTags&&u(n),n},p=function(e,t,n){for(var r=n.length;r--;)if(n[r].type===t&&n[r].name===e)return!0;return!1},c=function(t,n){var r=t?y.bbcodes[t.name]:null,o=r?r.allowedChildren:null;return y.opts.fixInvalidChildren&&o?o&&0>e.inArray(n.name||"#",o)?!1:!0:!0},l=function(t){for(var n,r,o,i,l,s,a,c=[],u=[],d=[],f=function(){return g(d)},h=function(e){f()?f().children.push(e):u.push(e)},b=function(t){return f()&&(r=y.bbcodes[f().name])&&r.closedBy&&e.inArray(t,r.closedBy)>-1};n=t.shift();){switch(a=t[0],n.type){case v.open:b(n.name)&&d.pop(),h(n),r=y.bbcodes[n.name],r&&r.isSelfClosing||!r&&!p(n.name,v.close,t)||d.push(n);break;case v.close:if(f()&&n.name!==f().name&&b("/"+n.name)&&d.pop(),f()&&n.name===f().name)f().closing=n,d.pop();else if(p(n.name,v.open,d)){for(;o=d.pop();){if(o.name===n.name){o.closing=n;break}i=o.clone(),c.length>1&&i.children.push(g(c)),c.push(i)}for(h(g(c)),l=c.length;l--;)d.push(c[l]);c.length=0}else n.type=v.content,h(n);break;case v.newline:f()&&a&&b((a.type===v.close?"/":"")+a.name)&&(a.type!==v.close||a.name!==f().name)&&(r=y.bbcodes[f().name],r&&r.breakAfter?d.pop():r&&r.isInline===!1&&y.opts.breakAfterBlock&&r.breakAfter!==!1&&d.pop()),h(n);break;default:h(n)}s=n}return u},s=function(e,t,n){var r,o,i,l,a,c,u,d,f=e.length,h=f;for(t&&(l=y.bbcodes[t.name]);h--;)if(r=e[h])if(r.type===v.newline){if(o=h>0?e[h-1]:null,i=f-1>h?e[h+1]:null,d=!1,!n&&l&&l.isSelfClosing!==!0&&(o?c||i||(l.isInline===!1&&y.opts.breakEndBlock&&l.breakEnd!==!1&&(d=!0),l.breakEnd&&(d=!0),c=d):(l.isInline===!1&&y.opts.breakStartBlock&&l.breakStart!==!1&&(d=!0),l.breakStart&&(d=!0))),o&&o.type===v.open&&(a=y.bbcodes[o.name])&&(n?a.isInline===!1&&(d=!0):(a.isInline===!1&&y.opts.breakAfterBlock&&a.breakAfter!==!1&&(d=!0),a.breakAfter&&(d=!0))),!n&&!u&&i&&i.type===v.open&&(a=y.bbcodes[i.name])&&(a.isInline===!1&&y.opts.breakBeforeBlock&&a.breakBefore!==!1&&(d=!0),a.breakBefore&&(d=!0),u=d,d)){e.splice(h,1);continue}d&&e.splice(h,1),u=!1}else r.type===v.open&&s(r.children,r,n)},a=function(t,n,r,o){var i,l,s,c,u,d,f=function(e){var t=y.bbcodes[e.name];return!t||t.isInline!==!1};for(n=n||[],o=o||t,l=0;t.length>l;l++)if((i=t[l])&&i.type===v.open){if(!f(i)&&r&&(s=g(n),d=s.splitAt(i),u=n.length>1?n[n.length-2].children:o,(c=e.inArray(s,u))>-1))return d.children.splice(e.inArray(i,d.children),1),u.splice(c+1,0,i,d),void 0;n.push(i),a(i.children,n,r||f(i),o),n.pop(i)}},d=function(e,t){for(var n,r,o=e.length;o--;)(n=e[o])&&(c(t,n)||(n.name=null,n.type=v.content,c(t,n)?(r=[o+1,0].concat(n.children),n.closing&&(n.closing.name=null,n.closing.type=v.content,r.push(n.closing)),o+=r.length-1,Array.prototype.splice.apply(e,r)):t.children.splice(o,1)),n.type===v.open&&d(n.children,n))},u=function(e){var t,n,r,o=e.length;for(r=function(e){for(var t=e.length;t--;){if(e[t].type===v.open)return!1;if(e[t].type===v.close)return!1;if(e[t].type===v.content&&e[t].val&&/\S|\u00A0/.test(e[t].val))return!1}return!0};o--;)(t=e[o])&&t.type===v.open&&(n=y.bbcodes[t.name],u(t.children),r(t.children)&&n&&!n.isSelfClosing&&!n.allowsEmpty&&e.splice(o,1))},y.toHTML=function(e,t){return f(y.parse(e,t),!0)},f=function(t,r){var o,i,l,s,a,c,u,d,h=[];for(u=function(e){return(!e||(e.isHtmlInline!==void 0?e.isHtmlInline:e.isInline))!==!1};t.length>0;)if(o=t.shift()){if(o.type===v.open)d=o.children[o.children.length-1]||{},i=y.bbcodes[o.name],a=r&&u(i),l=f(o.children,!1),i&&i.html?(u(i)||!u(y.bbcodes[d.name])||i.isPreFormatted||i.skipLastLineBreak||e.sceditor.ie||(l+="<br />"),s=e.isFunction(i.html)?i.html.call(y,o,o.attrs,l):e.sceditor.plugins.bbcode.formatString(i.html,l)):s=o.val+l+(o.closing?o.closing.val:"");else{if(o.type===v.newline){if(!r){h.push("<br />");continue}if(c){h.push("</div>\n"),c=!1;continue}h.push("<div>"),e.sceditor.ie||h.push("<br />"),(n.documentMode&&8>n.documentMode||8>e.sceditor.ie)&&h.push(" "),h.push("</div>\n");continue}a=r,s=e.sceditor.escapeEntities(o.val)}a&&!c?(h.push("<div>"),c=!0):!a&&c&&(h.push("</div>\n"),c=!1),h.push(s)}return c&&h.push("</div>\n"),h.join("")},y.toBBCode=function(e,t){return h(y.parse(e,t))},h=function(t){for(var n,r,o,i,l,s,a,c,u,d,f=[];t.length>0;)if(n=t.shift())if(o=y.bbcodes[n.name],i=!(!o||o.isInline!==!1),l=o&&o.isSelfClosing,a=i&&y.opts.breakBeforeBlock&&o.breakBefore!==!1||o&&o.breakBefore,c=i&&!l&&y.opts.breakStartBlock&&o.breakStart!==!1||o&&o.breakStart,u=i&&y.opts.breakEndBlock&&o.breakEnd!==!1||o&&o.breakEnd,d=i&&y.opts.breakAfterBlock&&o.breakAfter!==!1||o&&o.breakAfter,s=(o?o.quoteType:null)||y.opts.quoteType||e.sceditor.BBCodeParser.QuoteType.auto,o||n.type!==v.open)if(n.type===v.open){if(a&&f.push("\n"),f.push("["+n.name),n.attrs){n.attrs.defaultattr&&(f.push("="+b(n.attrs.defaultattr,s,"defaultattr")),delete n.attrs.defaultattr);for(r in n.attrs)n.attrs.hasOwnProperty(r)&&f.push(" "+r+"="+b(n.attrs[r],s,r))}f.push("]"),c&&f.push("\n"),n.children&&f.push(h(n.children)),l||o.excludeClosing||(u&&f.push("\n"),f.push("[/"+n.name+"]")),d&&f.push("\n"),n.closing&&l&&f.push(n.closing.val)}else f.push(n.val);else f.push(n.val),n.children&&f.push(h(n.children)),n.closing&&f.push(n.closing.val);return f.join("")},b=function(t,n,r){var o=e.sceditor.BBCodeParser.QuoteType,i=/\s|=/.test(t);return e.isFunction(n)?n(t,r):n===o.never||n===o.auto&&!i?t:'"'+t.replace("\\","\\\\").replace('"','\\"')+'"'},g=function(e){return e.length?e[e.length-1]:null},m=function(e){return e.toLowerCase()},r()},e.sceditor.BBCodeParser.QuoteType={always:1,never:2,auto:3},Object.freeze&&Object.freeze(e.sceditor.BBCodeParser.QuoteType),e.sceditor.BBCodeParser.defaults={breakBeforeBlock:!1,breakStartBlock:!1,breakEndBlock:!1,breakAfterBlock:!0,removeEmptyTags:!0,fixInvalidNesting:!0,fixInvalidChildren:!0,quoteType:e.sceditor.BBCodeParser.QuoteType.auto},e.sceditorBBCodePlugin=e.sceditor.plugins.bbcode=function(){var t,r,o,i,l,s,a,c=this;i=e.sceditor.plugins.bbcode.formatString,c.bbcodes=e.sceditor.plugins.bbcode.bbcodes,c.stripQuotes=e.sceditor.plugins.bbcode.stripQuotes;var u={},d={},f={ul:["li","ol","ul"],ol:["li","ol","ul"],table:["tr"],tr:["td","th"],code:["br","p","div"]},h={};c.init=function(){c.opts=this.opts,t(),s(this)},s=function(t){var n={bold:{txtExec:["[b]","[/b]"]},italic:{txtExec:["[i]","[/i]"]},underline:{txtExec:["[u]","[/u]"]},strike:{txtExec:["[s]","[/s]"]},subscript:{txtExec:["[sub]","[/sub]"]},superscript:{txtExec:["[sup]","[/sup]"]},left:{txtExec:["[left]","[/left]"]},center:{txtExec:["[center]","[/center]"]},right:{txtExec:["[right]","[/right]"]},justify:{txtExec:["[justify]","[/justify]"]},font:{txtExec:function(t){var n=this;e.sceditor.command.get("font")._dropDown(n,t,function(e){n.insertText("[font="+e+"]","[/font]")})}},size:{txtExec:function(t){var n=this;e.sceditor.command.get("size")._dropDown(n,t,function(e){n.insertText("[size="+e+"]","[/size]")})}},color:{txtExec:function(t){var n=this;e.sceditor.command.get("color")._dropDown(n,t,function(e){n.insertText("[color="+e+"]","[/color]")})}},bulletlist:{txtExec:["[ul][li]","[/li][/ul]"]},orderedlist:{txtExec:["[ol][li]","[/li][/ol]"]},table:{txtExec:["[table][tr][td]","[/td][/tr][/table]"]},horizontalrule:{txtExec:["[hr]"]},code:{txtExec:["[code]","[/code]"]},image:{txtExec:function(e,t){var n=prompt(this._("Enter the image URL:"),t);n&&this.insertText("[img]"+n+"[/img]")}},email:{txtExec:function(e,t){var n=t&&t.indexOf("@")>-1?null:t,r=prompt(this._("Enter the e-mail address:"),n?"":t),o=prompt(this._("Enter the displayed text:"),n||r)||r;r&&this.insertText("[email="+r+"]"+o+"[/email]")}},link:{txtExec:function(e,t){var n=t&&t.indexOf("http://")>-1?null:t,r=prompt(this._("Enter URL:"),n?"http://":t),o=prompt(this._("Enter the displayed text:"),n||r)||r;r&&this.insertText("[url="+r+"]"+o+"[/url]")}},quote:{txtExec:["[quote]","[/quote]"]},youtube:{txtExec:function(t){var n=this;e.sceditor.command.get("youtube")._dropDown(n,t,function(e){n.insertText("[youtube]"+e+"[/youtube]")})}},rtl:{txtExec:["[rtl]","[/rtl]"]},ltr:{txtExec:["[ltr]","[/ltr]"]}};t.commands=e.extend(!0,{},n,t.commands)},t=function(){e.each(c.bbcodes,function(t){c.bbcodes[t].tags&&e.each(c.bbcodes[t].tags,function(e,n){var r=c.bbcodes[t].isInline===!1;u[e]=u[e]||{},u[e][r]=u[e][r]||{},u[e][r][t]=n}),c.bbcodes[t].styles&&e.each(c.bbcodes[t].styles,function(e,n){var r=c.bbcodes[t].isInline===!1;d[r]=d[r]||{},d[r][e]=d[r][e]||{},d[r][e][t]=n})})},l=function(t,n){var r,o,i,l,s,a=t.style;return a?(h[n]||(h[n]=e.camelCase(n)),s=h[n],"text-align"===n?(r=e(t),i=a.direction,l=a[s]||r.css(n),r.parent().css(n)===l||"block"!==r.css("display")||r.is("hr")||r.is("th")||(o=l),i&&o&&(/right/i.test(o)&&"rtl"===i||/left/i.test(o)&&"ltr"===i)?null:o):a[s]):null},r=function(t,n,r){var o;return r=!!r,d[r]?(e.each(d[r],function(r,s){o=l(t[0],r),o&&l(t.parent()[0],r)!==o&&e.each(s,function(r,l){(!l||e.inArray(""+o,l)>-1)&&(n=e.isFunction(c.bbcodes[r].format)?c.bbcodes[r].format.call(c,t,n):i(c.bbcodes[r].format,n))})}),n):n},o=function(t,n,r){var o=t[0].nodeName.toLowerCase();if(r=!!r,u[o]&&u[o][r]&&e.each(u[o][r],function(r,o){if(o){var l=!1;if(e.each(o,function(n,r){return!t.attr(n)||r&&0>e.inArray(t.attr(n),r)?void 0:(l=!0,!1)}),!l)return}n=e.isFunction(c.bbcodes[r].format)?c.bbcodes[r].format.call(c,t,n):i(c.bbcodes[r].format,n)}),r&&(!e.sceditor.dom.isInline(t[0],!0)||"br"===o)){for(var l=t[0].parentNode,s=t[0].previousSibling,a=e.sceditor.dom.isInline(l,!0)||"body"===l.nodeName.toLowerCase();s&&e(s).hasClass("sceditor-ignore");)s=s.previousSibling;(a||l.lastChild!==t[0]||"li"===o||"br"===o&&e.sceditor.ie)&&(n+="\n"),"br"!==o&&s&&"br"!=s.nodeName.toLowerCase()&&e.sceditor.dom.isInline(s,!0)&&(n="\n"+n)}return n},c.signalToSource=function(t,n){var r=new e.sceditor.BBCodeParser(c.opts.parserOptions);return e.sceditor.dom.removeWhiteSpace(n[0]),e.trim(r.toBBCode(c.elementToBbcode(n),!0))},c.elementToBbcode=function(t){return function n(t,i){var l="";return e.sceditor.dom.traverse(t,function(t){var s=e(t),a="",c=t.nodeType,u=t.nodeName.toLowerCase(),d=f[u],h=!0;if("object"==typeof i&&(h=e.inArray(u,i)>-1,h||(d=i)),3===c||1===c)if(1===c){if(s.hasClass("sceditor-ignore"))return;"iframe"!==u&&(a=n(t,d)),h?("code"!==u&&(a=r(s,a),a=o(s,a),a=r(s,a,!0)),l+=o(s,a,!0)):l+=a}else!t.wholeText||t.previousSibling&&3===t.previousSibling.nodeType?t.wholeText||(l+=t.nodeValue):l+=0===s.parents("code").length?t.wholeText.replace(/ +/g," "):t.wholeText},!1,!0),l}(t.get(0))},c.signalToWysiwyg=function(t,n){var r=new e.sceditor.BBCodeParser(c.opts.parserOptions),o=r.toHTML(t);return n?a(o):o},a=function(t){var r,o,i,l=e("<div />").hide().appendTo(n.body),s=l[0];return i=function(t,r){for(;o=t.firstChild;)s.insertBefore(o,t);if(r){var i=s.lastChild;t!==i&&e(i).is("div")&&t.nextSibling===i&&s.insertBefore(n.createElement("br"),t)}s.removeChild(t)},s.innerHTML=t.replace(/<\/div>\n/g,"</div>"),(r=s.firstChild)&&e(r).is("div")&&i(r,!0),(r=s.lastChild)&&e(r).is("div")&&i(r),s=s.innerHTML,l.remove(),s}},e.sceditor.plugins.bbcode.stripQuotes=function(e){return e?e.replace(/\\(.)/g,"$1").replace(/^(["'])(.*?)\1$/,"$2"):e},e.sceditor.plugins.bbcode.formatString=function(){var e=arguments;return e[0].replace(/\{(\d+)\}/g,function(t,n){return e[n-0+1]!==void 0?e[n-0+1]:"{"+n+"}"})},e.sceditor.plugins.bbcode.normaliseColour=function(e){function t(e){return e=parseInt(e,10),isNaN(e)?"00":(e=Math.max(0,Math.min(e,255)).toString(16),2>e.length?"0"+e:e)}var n;return(n=e.match(/rgb\((\d{1,3}),\s*?(\d{1,3}),\s*?(\d{1,3})\)/i))?"#"+t(n[1])+t(n[2]-0)+t(n[3]-0):(n=e.match(/#([0-f])([0-f])([0-f])\s*?$/i))?"#"+n[1]+n[1]+n[2]+n[2]+n[3]+n[3]:e},e.sceditor.plugins.bbcode.bbcodes={b:{tags:{b:null,strong:null},styles:{"font-weight":["bold","bolder","401","700","800","900"]},format:"[b]{0}[/b]",html:"<strong>{0}</strong>"},i:{tags:{i:null,em:null},styles:{"font-style":["italic","oblique"]},format:"[i]{0}[/i]",html:"<em>{0}</em>"},u:{tags:{u:null},styles:{"text-decoration":["underline"]},format:"[u]{0}[/u]",html:"<u>{0}</u>"},s:{tags:{s:null,strike:null},styles:{"text-decoration":["line-through"]},format:"[s]{0}[/s]",html:"<s>{0}</s>"},sub:{tags:{sub:null},format:"[sub]{0}[/sub]",html:"<sub>{0}</sub>"},sup:{tags:{sup:null},format:"[sup]{0}[/sup]",html:"<sup>{0}</sup>"},font:{tags:{font:{face:null}},styles:{"font-family":null},quoteType:e.sceditor.BBCodeParser.QuoteType.never,format:function(e,t){var n;return"font"===e[0].nodeName.toLowerCase()&&(n=e.attr("face"))||(n=e.css("font-family")),"[font="+this.stripQuotes(n)+"]"+t+"[/font]"},html:function(e,t,n){return'<font face="'+t.defaultattr+'">'+n+"</font>"}},size:{tags:{font:{size:null}},styles:{"font-size":null},format:function(e,t){var n=e.attr("size"),r=1;return n||(n=e.css("fontSize")),n.indexOf("px")>-1?(n=n.replace("px","")-0,n>12&&(r=2),n>15&&(r=3),n>17&&(r=4),n>23&&(r=5),n>31&&(r=6),n>47&&(r=7)):r=n,"[size="+r+"]"+t+"[/size]"},html:function(e,t,n){return'<font size="'+t.defaultattr+'">'+n+"</font>"}},color:{tags:{font:{color:null}},styles:{color:null},quoteType:e.sceditor.BBCodeParser.QuoteType.never,format:function(t,n){var r,o=t[0];return"font"===o.nodeName.toLowerCase()&&(r=t.attr("color"))||(r=o.style.color||t.css("color")),"[color="+e.sceditor.plugins.bbcode.normaliseColour(r)+"]"+n+"[/color]"},html:function(e,t,n){return'<font color="'+t.defaultattr+'">'+n+"</font>"}},ul:{tags:{ul:null},breakStart:!0,isInline:!1,skipLastLineBreak:!0,format:"[ul]{0}[/ul]",html:"<ul>{0}</ul>"},list:{breakStart:!0,isInline:!1,skipLastLineBreak:!0,html:"<ul>{0}</ul>"},ol:{tags:{ol:null},breakStart:!0,isInline:!1,skipLastLineBreak:!0,format:"[ol]{0}[/ol]",html:"<ol>{0}</ol>"},li:{tags:{li:null},isInline:!1,closedBy:["/ul","/ol","/list","*","li"],format:"[li]{0}[/li]",html:"<li>{0}</li>"},"*":{isInline:!1,closedBy:["/ul","/ol","/list","*","li"],html:"<li>{0}</li>"},table:{tags:{table:null},isInline:!1,isHtmlInline:!0,skipLastLineBreak:!0,format:"[table]{0}[/table]",html:"<table>{0}</table>"},tr:{tags:{tr:null},isInline:!1,skipLastLineBreak:!0,format:"[tr]{0}[/tr]",html:"<tr>{0}</tr>"},th:{tags:{th:null},allowsEmpty:!0,isInline:!1,format:"[th]{0}[/th]",html:"<th>{0}</th>"},td:{tags:{td:null},allowsEmpty:!0,isInline:!1,format:"[td]{0}[/td]",html:"<td>{0}</td>"},emoticon:{allowsEmpty:!0,tags:{img:{src:null,"data-sceditor-emoticon":null}},format:function(e,t){return e.attr("data-sceditor-emoticon")+t},html:"{0}"},hr:{tags:{hr:null},allowsEmpty:!0,isSelfClosing:!0,isInline:!1,format:"[hr]{0}",html:"<hr />"},img:{allowsEmpty:!0,tags:{img:{src:null}},quoteType:e.sceditor.BBCodeParser.QuoteType.never,format:function(t,n){var r="",o=function(e){return t.style?t.style[e]:null};return t.attr("data-sceditor-emoticon")!==void 0?n:((t.attr("width")||t.attr("height")||o("width")||o("height"))&&(r="="+e(t).width()+"x"+e(t).height()),"[img"+r+"]"+t.attr("src")+"[/img]")},html:function(e,t,n){var r,o="";return t.width!==void 0&&(o+=' width="'+t.width+'"'),t.height!==void 0&&(o+=' height="'+t.height+'"'),t.defaultattr!==void 0&&(r=t.defaultattr.split(/x/i),o=' width="'+r[0]+'"'+' height="'+(2===r.length?r[1]:r[0])+'"'),"<img"+o+' src="'+n+'" />'}},url:{allowsEmpty:!0,tags:{a:{href:null}},quoteType:e.sceditor.BBCodeParser.QuoteType.never,format:function(e,t){var n=e.attr("href");return"mailto:"===n.substr(0,7)?'[email="'+n.substr(7)+'"]'+t+"[/email]":"[url="+decodeURI(n)+"]"+t+"[/url]"},html:function(e,t,n){return(t.defaultattr===void 0||0===t.defaultattr.length)&&(t.defaultattr=n),'<a href="'+encodeURI(t.defaultattr)+'">'+n+"</a>"}},email:{quoteType:e.sceditor.BBCodeParser.QuoteType.never,html:function(e,t,n){return t.defaultattr===void 0&&(t.defaultattr=n),'<a href="mailto:'+t.defaultattr+'">'+n+"</a>"}},quote:{tags:{blockquote:null},isInline:!1,quoteType:e.sceditor.BBCodeParser.QuoteType.never,format:function(t,n){var r="",o=e(t),i=o.children("cite").first();return(1===i.length||o.data("author"))&&(r=i.text()||o.data("author"),o.data("author",r),i.remove(),o.children("cite").replaceWith(function(){return e(this).text()}),n=this.elementToBbcode(e(t)),r="="+r),"[quote"+r+"]"+n+"[/quote]"},html:function(e,t,n){return t.defaultattr!==void 0&&(n="<cite>"+t.defaultattr+"</cite>"+n),"<blockquote>"+n+"</blockquote>"}},code:{tags:{code:null},isInline:!1,allowedChildren:["#","#newline"],format:"[code]{0}[/code]",html:"<code>{0}</code>"},left:{styles:{"text-align":["left","-webkit-left","-moz-left","-khtml-left"]},isInline:!1,format:"[left]{0}[/left]",html:'<div align="left">{0}</div>'},center:{styles:{"text-align":["center","-webkit-center","-moz-center","-khtml-center"]},isInline:!1,format:"[center]{0}[/center]",html:'<div align="center">{0}</div>'},right:{styles:{"text-align":["right","-webkit-right","-moz-right","-khtml-right"]},isInline:!1,format:"[right]{0}[/right]",html:'<div align="right">{0}</div>'},justify:{styles:{"text-align":["justify","-webkit-justify","-moz-justify","-khtml-justify"]},isInline:!1,format:"[justify]{0}[/justify]",html:'<div align="justify">{0}</div>'},youtube:{allowsEmpty:!0,tags:{iframe:{"data-youtube-id":null}},format:function(e,t){return(e=e.attr("data-youtube-id"))?"[youtube]"+e+"[/youtube]":t},html:'<iframe width="560" height="315" src="http://www.youtube.com/embed/{0}?wmode=opaque" data-youtube-id="{0}" frameborder="0" allowfullscreen></iframe>'},rtl:{styles:{direction:["rtl"]},format:"[rtl]{0}[/rtl]",html:'<div style="direction: rtl">{0}</div>'},ltr:{styles:{direction:["ltr"]},format:"[ltr]{0}[/ltr]",html:'<div style="direction: ltr">{0}</div>'},ignore:{}},e.sceditor.plugins.bbcode.bbcode={get:function(t){return e.sceditor.plugins.bbcode.bbcodes[t]||null},set:function(t,n){return t&&n?(n=e.extend(e.sceditor.plugins.bbcode.bbcodes[t]||{},n),n.remove=function(){e.sceditor.plugins.bbcode.bbcode.remove(t)},e.sceditor.plugins.bbcode.bbcodes[t]=n,this):!1},rename:function(e,t){return this.hasOwnProperty(e)?(this[t]=this[e],this.remove(e),this):!1},remove:function(t){return e.sceditor.plugins.bbcode.bbcodes[t]&&delete e.sceditor.plugins.bbcode.bbcodes[t],this}},e.fn.sceditorBBCodePlugin=function(t){return t=t||{},e.isPlainObject(t)&&(t.plugins=(t.plugins?t.plugins:"")+"bbcode"),this.sceditor(t)}})(jQuery,window,document);