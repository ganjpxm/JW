/**
 * jp javascript Library 1.0.0
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * jp Project
 */
jp = {
  version: '1.0.0',
  author: 'GanJianping',
  versionDetail: {
    major: 1,
    minor: 0,
    patch: 0
  }
};

/**
 * Copies all the properties of config to obj.
 * @param {Object} aReceiverObj The receiver of the properties
 * @param {Object} aSourceConfig The source of the properties
 * @param {Object} aDefaultConfig defaults A different object that will also be applied for default values
 * @return {Object} returns obj
 * @author GanJianping
 * @since 1.0.0
 */
jp.apply = function(aReceiverObj, aSourceConfig, aDefaultConfig){
  if (aDefaultConfig) {
    jp.apply(aReceiverObj, aDefaultConfig);
  }
  if (aReceiverObj && aSourceConfig && typeof aSourceConfig == 'object') {
    for (var key in aSourceConfig) {
      aReceiverObj[key] = aSourceConfig[key];
    }
  }
  return aReceiverObj;
};
//alert(navigator.userAgent.toLowerCase());
/**
 * jp extend method
 *
 * @author GanJianping
 * @since 1.0.0
 */
(function(){
  var toString = Object.prototype.toString,
    userAgent = navigator.userAgent.toLowerCase(),
    checkBrower = function(aStr){
      return aStr.test(userAgent);
    },
    DOC = document,
    //IE supoort ActiveX
    isIE = !isOpera && checkBrower(/msie/),
    isIE5 = isIE && checkBrower(/msie 5/), //Mozilla/4.0 (compatible; MSIE 5.0; Windows NT)
    isIE6 = isIE && checkBrower(/msie 6/), //Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1) 
    isIE7 = isIE && checkBrower(/msie 7/), //Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2)
    isIE8 = isIE && checkBrower(/msie 8/), //Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)
    isIE9 = isIE && checkBrower(/msie 9/),
    //Firefox's DOM Elements have getBoxObjectFor() function,get DOM Element position and size
    isFirefox = !checkBrower(/webkit/) && checkBrower(/gecko/), 
    isFirefox1 = isFirefox && checkBrower(/firefox\/1/),//  Mozilla/5.0 (Windows; U; Windows NT 5.1) Gecko/20070803 Firefox/1.5.0.12 
    isFirefox2 = isFirefox && checkBrower(/rv:1\.8/),//Mozilla/5.0 (Windows; U; Windows NT 5.1) Gecko/20070309 Firefox/2.0.0.3 
    isFirefox3 = isFirefox && checkBrower(/rv:1\.9/),//Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1
    isFirefox4 = isFirefox && checkBrower(/firefox\/4/),
    //Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13
    isChrome = checkBrower(/chrome\/([\d.]+)/),
    isChrome1 = checkBrower(/chrome\/1/),
    isChrome2 = checkBrower(/chrome\/2/),
    isChrome3 = checkBrower(/chrome\/3/),
    isChrome4 = checkBrower(/chrome\/4/),
    isChrome5 = checkBrower(/chrome\/5/),
    isChrome6 = checkBrower(/chrome\/6/),
    isChrome7 = checkBrower(/chrome\/7/),
    isChrome8 = checkBrower(/chrome\/8/),
    //Opera window.opera attribution
    isOpera = checkBrower(/opera/),
    isOpera8 = checkBrower(/opera\/8/), //Opera/8.0 (Macintosh; PPC Mac OS X; U; en)
    isOpera9 = checkBrower(/opera\/9/), //Opera/9.27 (Windows NT 5.2; U; zh-cn)
    //Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML, like Gecko) Version/3.1 Safari/525.13
    //Mozilla/5.0 (iPhone; U; CPU like Mac OS X) AppleWebKit/420.1 (KHTML, like Gecko) Version/3.0 Mobile/4A93 Safari/419.3 
    isSafari = !isChrome && checkBrower(/safari/),
    isSafari2 = isSafari && checkBrower(/applewebkit\/4/), 
    isSafari3 = isSafari && checkBrower(/version\/3/),
    isSafari4 = isSafari && checkBrower(/version\/4/),
    isWindows = checkBrower(/windows|win32/),
    isLinux = checkBrower(/linux/),
    isMac = checkBrower(/macintosh|mac os x/),
    isAir = checkBrower(/adobeair/),
    isWebKit = checkBrower(/webkit/),
    isSecure = /^https/i.test(window.location.protocol);
  //remove css image flicker
  if(isIE6){
    try{
      DOC.execCommand("BackgroundImageCache", false, true);
    }catch(e){}
  }
  function f(n) {
    return n < 10 ? '0' + n : n;// Format integers to have at least two digits.
  }
  if (typeof Date.prototype.toJSON !== 'function') {
    Date.prototype.toJSON = function (key) {
      return isFinite(this.valueOf()) ?
        this.getUTCFullYear()     + '-' +
        f(this.getUTCMonth() + 1) + '-' +
        f(this.getUTCDate())      + 'T' +
        f(this.getUTCHours())     + ':' +
        f(this.getUTCMinutes())   + ':' +
        f(this.getUTCSeconds())   + 'Z' : null;
    };
    String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = 
      function (key) {return this.valueOf();};
  }
  jp.apply(jp, {
    isIE : isIE,
    isIE6 : isIE6,
    isIE7 : isIE7,
    isIE8 : isIE8,
    isIE9 : isIE9,
    isFirefox : isFirefox,
    isFirefox1 : isFirefox1,
    isFirefox2 : isFirefox2,
    isFirefox3 : isFirefox3,
    isFirefox4 : isFirefox4,
    isChrome : isChrome,
    isChrome1 : isChrome1,
    isChrome2 : isChrome2,
    isChrome3 : isChrome3,
    isChrome4 : isChrome4,
    isChrome5 : isChrome5,
    isChrome6 : isChrome6,
    isChrome7 : isChrome7,
    isChrome8 : isChrome8,
    isOpera : isOpera,
    isOpera8 : isOpera8,
    isOpera9 : isOpera9,
    isSafari : isSafari,
    isSafari2 : isSafari2,
    isSafari3 : isSafari3,
    isSafari4 : isSafari4,
    isWindows : isWindows,
    isLinux : isLinux,
    isMac : isMac,
    isAir : isAir,
    isSecure : isSecure,
    //--------------------------------------------- base method  ---------------------------------------------
    /**
     * <p>return true if aValueMix has been defined</p>
     * 
     * @param {Mixed} value 
     * @return {Boolean}
     */
    isDefined : function(aValueMix){
      return typeof aValueMix !== 'undefined';
    },
    /**
     * <p>return true if aValueMix is String</p>
     * 
     * @param {Mixed} value
     * @return {Boolean}
     */
    isString : function(aValueMix){
      return typeof aValueMix === 'string';
    },
    /**
     * <p>return true if aValueMix is boolean</p>
     * 
     * @param {Mixed} value
     * @return {Boolean}
     */
    isBoolean : function(aValueMix){
      return typeof aValueMix === 'boolean';
  	},
    /**
     * <p>return true if aValueMix is number or finite</p>
     * 
     * @param {Mixed} value 
     * @return {Boolean}
     */
    isNumber : function(aValueMix){
        return typeof aValueMix === 'number' && isFinite(aValueMix);
    },
    /**
     * <p>return true if aValueMix is string, number or boolean</p>
     * 
     * @param {Mixed} value
     * @return {Boolean}
     */
    isPrimitive : function(aValueMix){
       return jp.isString(aValueMix) || jp.isNumber(aValueMix) || jp.isBoolean(aValueMix);
    },
    /**
     * <p>return true if aValueMix is array</p>
     * @param {Mixed} value 需要判断的值
     * @return {Boolean}
     */
    isArray : function(aValueMix) {
      return toString.apply(aValueMix) === '[object Array]';
    },
    /**
     * <p>return true if aValueMix is empty</p>
     * 
     * @param {Mixed} value 
     * @param {Boolean} 
     * @return {Boolean}
     */
    isEmpty : function(aValueMix, aAllowBlank){
      return aValueMix === null || aValueMix === undefined || ((jp.isArray(aValueMix) && !aValueMix.length)) || (!aAllowBlank ? aValueMix === '' : false);
    },
    /**
     * <p>return true if aValueMix is Iterable</p>
     * 
     * @param {Mixed} value 
     * @return {Boolean}
     */
    isIterable : function(aValueMix){
      if (jp.isArray(aValueMix) || aValueMix.callee) {
        return true;
      }
      if(/NodeList|HTMLCollection/.test(toString.call(aValueMix))){
        return true;
      }
      return ((typeof aValueMix.nextNode != 'undefined' || aValueMix.item) && jp.isNumber(aValueMix.length));
    },
    /**
     * <p>delete left whitespace</p>
     * eg: jp.ltrim(" ganjp")
     *  
     * @param {String}
     * @return (String|mix)
     */
    ltrim : function(aStr){
      if (!jp.isString(aStr)) {
        return aStr;
      }
      return aStr.replace(/^\s*/, "");
    },
    /**
     * <p>delete right whitespace</p>
     * eg: jp.rtrim(" ganjp ") 
     * 
     * @param {String}
     * @return (String|mix)
     */
   rtrim : function(aStr){
      if (!jp.isString(aStr)) {
        return aStr;
      }
      return aStr.replace(/\s*$/, "");
    },
    /**
     * <p>delete both whitespace</p>
     * eg: jp.trim(" ganjp")
     *  
     * @param {String}
     * @return (String|mix)
     */
    trim : function(aStr){
      if (!jp.isString(aStr)) {
        return aStr;
      }
      return jp.rtrim(jp.ltrim(aStr));
    },
    /**
     * <p>Converts any iterable into a true array</p>
     * eg: jp.toArray("23,24,26",1,2)
     *  
     * @param {Iterable or String}
     * @return (Array) array
     */
    toArray : function(){
      return isIE ?
        function(a, i, j, res) {
          if (typeof a === 'string') {
            res = a.split(",");
          } else {
            res = [];
            for (var x = 0, len = a.length; x < len; x++) {
              res.push(a[x]);
            }
          }
          return res.slice(i || 0, j || res.length);
        } :
        function(a, i, j) {
          var arr;  
          if (typeof a === 'string') {
            arr = a.split(",");
          } else {
            arr = a;
          }
          return Array.prototype.slice.call(arr, i || 0, j || arr.length);
        }
    }(),
    toJson : function(text, reviver){
      var j,cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
      function walk(holder, key) {
        var k, v, value = holder[key];
        if (value && typeof value === 'object') {
          for (k in value) {
            if (Object.prototype.hasOwnProperty.call(value, k)) {
              v = walk(value, k);
              if (v !== undefined) {
                value[k] = v;
              } else {
                delete value[k];
              }
            }
          }
        }
        return reviver.call(holder, key, value);
      }
      text = String(text);
      cx.lastIndex = 0;
      if (cx.test(text)) {
          text = text.replace(cx, function (a) {
              return '\\u' +
                  ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
          });
      }
      if (/^[\],:{}\s]*$/
              .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@')
                  .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
                  .replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
          j = eval('(' + text + ')');
          return typeof reviver === 'function' ? walk({'': j}, '') : j;
      }
      throw new SyntaxError('JSON.parse');
    },
    quote: function(string){
      var escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
          meta = {'\b': '\\b', '\t': '\\t', '\n': '\\n', '\f': '\\f', '\r': '\\r', '"' : '\\"', '\\': '\\\\'};
          escapable.lastIndex = 0;
      return escapable.test(string) ? '"' + string.replace(escapable, function (a) {var c = meta[a];
               return typeof c === 'string' ? c : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
           }) + '"' : '"' + string + '"';
    },
    //jp.toStr(newNodeArr)
    toStr: function(value, replacer, space){
      var gap='', indent='', rep;
      function str(key, holder) {
        var i,          // The loop counter.
            k,          // The member key.
            v,          // The member value.
            length,
            mind = gap,
            partial,
            value = holder[key];
        if (typeof value.toJSON === 'function') {
            value = value.toJSON(key);
        }
        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }
        switch (typeof value) {
        case 'string':
            return jp.quote(value);
        case 'number':
            return isFinite(value) ? String(value) : 'null';
        case 'boolean':
        case 'null':
            return String(value);
        case 'object':
            if (!value) {
                return 'null';
            }
            gap += indent;
            partial = [];
            if (Object.prototype.toString.apply(value) === '[object Array]') {
                length = value.length;
                for (i = 0; i < length; i += 1) {
                    partial[i] = str(i, value) || 'null';
                }
                v = partial.length === 0 ? '[]' : gap ?
                    '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']' :
                    '[' + partial.join(',') + ']';
                gap = mind;
                return v;
            }
            if (rep && typeof rep === 'object') {
                length = rep.length;
                for (i = 0; i < length; i += 1) {
                    if (typeof rep[i] === 'string') {
                        k = rep[i];
                        v = str(k, value);
                        if (v) {
                            partial.push(jp.quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            } else {
                for (k in value) {
                    if (Object.prototype.hasOwnProperty.call(value, k)) {
                        v = str(k, value);
                        if (v) {
                            partial.push(jp.quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            }
            v = partial.length === 0 ? '{}' : gap ?
                '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}' :
                '{' + partial.join(',') + '}';
            gap = mind;
            return v;
        }
      }
      if (typeof space === 'number') {
        for (var i = 0; i < space; i += 1) {
          indent += ' ';
        }
      } else if (typeof space === 'string') {
        indent = space;
      }
      rep = replacer;
      if (replacer && typeof replacer !== 'function' &&
              (typeof replacer !== 'object' ||
              typeof replacer.length !== 'number')) {
          throw new Error('jp.toStr');
      }
      return str('', {'': value});
    },
    /**
     * <p>Circulation perform a array</p>
     * 
     * @param {Array/NodeList/Mixed} array The array to be iterated. 
     * @param {Function} fn The function to be called with each item. 
     * @param {Object} scope this
     * @return See description for the fn parameter.
     */
    each: function(pArr, pFn, pScope) {
      if (jp.isEmpty(pArr, true)) {
        return;
      }
      if (!jp.isIterable(pArr) || jp.isPrimitive(pArr)) {
        pArr = [pArr];
      }
      for(var i = 0, len = pArr.length; i < len; i++){
        if (pFn.call(pScope || pArr[i], pArr[i], i, pArr) === false){
          return i;
        };
      }
    },
    /**
     * Iterates either the elements in an array, or each of the properties in an object.
     * <b>Note</b>: If you are only iterating arrays, it is better to call {@link #each}.
     * @param {Object/Array} object The object or array to be iterated
     * @param {Function} fn The function to be called for each iteration.
     * The iteration will stop if the supplied function returns false, or
     * all array elements / object properties have been covered. The signature
     * varies depending on the type of object being interated:
     * <div class="mdetail-params"><ul>
     * <li>Arrays : <tt>(Object item, Number index, Array allItems)</tt>
     * <div class="sub-desc">
     * When iterating an array, the supplied function is called with each item.</div></li>
     * <li>Objects : <tt>(String key, Object value, Object)</tt>
     * <div class="sub-desc">
     * When iterating an object, the supplied function is called with each key-value pair in
     * the object, and the iterated object</div></li>
     * </ul></div>
     * @param {Object} scope The scope (<code>this</code> reference) in which the specified function is executed. Defaults to
     * the <code>object</code> being iterated.
     */
    iterate : function(obj, fn, scope){
      if (jp.isEmpty(obj)) {
        return;
      }
      if (jp.isIterable(obj)) {
        jp.each(obj, fn, scope);
        return;
      } else if (jp.isObject(obj)) {
	    for (var prop in obj){
	      if (obj.hasOwnProperty(prop)) {
	        if (fn.call(scope || obj, prop, obj[prop], obj) === false) {
	          return;
	        };
	      }
	    }
      }
    },
    /**
     * <p>aSourceConfig value can not override aReceiverObj value, only can add
     * 
     * @param {Object} aReceiverObj 
     * @param {Object} aSourceConfig
     * @return {Object} returns obj
     */
    applyIf : function(aReceiverObj, aSourceConfig){
      if (aReceiverObj) {
          for (var p in aSourceConfig) {
              if (!jp.isDefined(aReceiverObj[p])) {
                aReceiverObj[p] = aSourceConfig[p];
              }
          }
      }
      return aReceiverObj;
    },
    /**
     * Adds a list of functions to the prototype of an existing class, 
     * overwriting any existing methods with the same name.
     * Usage:<pre><code>
		jp.override(MyClass, {
    		newMethod: function(){}
	   });</code></pre>
	 *  
     * @param {Object} origclass The class to override
     * @param {Object} overrides The list of functions to add to origClass.  This should be specified as an object literal
     * containing one or more methods.
     * @method override
     */
    override : function(aOrigclass, aOverrides){
      if(overrides){
        var p = aOrigclass.prototype;
        jp.apply(p, aOverrides);
        if(jp.isIE && aOverrides.hasOwnProperty('toString')){
          p.toString = aOverrides.toString;
        }
      }
    },
    /**
     * Takes an object and converts it to an encoded URL. e.g. jp.urlEncode({foo: 1, bar: 2}); 
     * would return "foo=1&bar=2".  Optionally, property values can be arrays, instead of keys and 
     * the resulting string that's returned will contain a name/value pair for each array value.
     * @param {Object} o
     * @param {String} pre (optional) A prefix to add to the url encoded string
     * @return {String}
     */
    urlEncode : function(aObj, aPreStr){
      var empty,
          buf = [],
          e = encodeURIComponent;

      jp.iterate(aObj, function(key, item){
          empty = jp.isEmpty(item);
          jp.each(empty ? key : item, function(val){
              buf.push('&', e(key), '=', (!jp.isEmpty(val) && (val != key || !empty)) 
              ? (jp.isDate(val) ? jp.encode(val).replace(/"/g, '') : e(val)) : '');
          });
      });
      if(!aPreStr){
          buf.shift();
          aPreStr = '';
      }
      return aPreStr + buf.join('');
    },
    /**
     * Takes an encoded URL and and converts it to an object. Example: <pre><code>
		jp.urlDecode("foo=1&bar=2"); // returns {foo: "1", bar: "2"}
		jp.urlDecode("foo=1&bar=2&bar=3&bar=4", false); // returns {foo: "1", bar: ["2", "3", "4"]}</code></pre>
	 *
     * @param {String} string
     * @param {Boolean} overwrite (optional) Items of the same name will overwrite previous values instead of 
     *                  creating an an array (Defaults to false).
     * @return {Object} A literal with members
     */
    urlDecode : function(aStr, overwrite){
      if(jp.isEmpty(aStr)){
        return {};
      }
      var obj = {},
          pairs = aStr.split('&'),
          d = decodeURIComponent,
          name,
          value;
      jp.each(pairs, function(pair) {
        pair = pair.split('=');
        name = d(pair[0]);
        value = d(pair[1]);
        obj[name] = overwrite || !obj[name] ? value :
                    [].concat(obj[name]).concat(value);
      });
      return obj;
    },

    /**
     * Appends content to the query string of a URL, handling logic for whether to place
     * a question mark or ampersand.
     * @param {String} url The URL to append to.
     * @param {String} s The content to append to the URL.
     * @return (String) The resulting URL
     */
    urlAppend : function(aUrl, aStr){
      if(!jp.isEmpty(aStr)){
        return aUrl + (aUrl.indexOf('?') === -1 ? '?' : '&') + aStr;
      }
      return aUrl;
    },
    //---------------------------------------------- operate dom elements --------------------------------
    /**
     * <p>Return the dom node for the passed String (id), dom node, or jp.Element.</p>
     * @param {Mixed} el
     * @return HTMLElement
     */
    getDom : function(el){
      if(!el || !DOC){
          return null;
      }
      return el.dom ? el.dom : (jp.isString(el) ? DOC.getElementById(el) : el);
    },
    pressCharacter : function(event){
   	 var keycode = event.keyCode;
   	  if( keycode>=97 && keycode<=122 )
   	  {
   	    event.keyCode = keycode-32;
   	  }
   	  return String.fromCharCode(event.keyCode);
    },  
    //--------------------------------------------- operate form ---------------------------------------------
    /**
     * <p>create form<p>
     * eg: jp.createForm({name:'form',action:'test.jsp',method:'post'});
     *
     * @param {json} aFormJson
     * @return {element} form form.appendChild( createHidden("method", "delete") );
     */
    createForm : function(aFormJson){
      var formEl = jp.isDefined(aFormJson.name)?DOC.createElement(aFormJson.name):DOC.createElement("form");
      if (aFormJson.action) {
        formEl.action = action;
      } else {
        alert("必须设置action属性");
        return;
      }
      formEl.method = jp.isDefined(aFormJson.method)?aFormJson.method:"post";
      //form.style.display = jp.isDefined(aFormJson.display)?aFormJson.display:"none";
        return formEl;
    },
    /**
     * <p>create hidden field</p>
     * 
     * @param {string} aElementName
     * @param {string} aValueStr
     * @return {element} 隐藏域
     */
     createHidden : function(aElementName, aValueStr){
        var inputEl = DOC.createElement("input");
        inputEl.type = "hidden";
        inputEl.id = aElementName;
        inputEl.name = aElementName;
        inputEl.value = aValueStr;
        return inputEl;
    },
    /**
     * <p>set field is only read</p>
     *
     * @param formId
     * @param inputName
     */
    setReadOnly : function(aFormId,aInputName){
      DOC.getElementById(aFormId).elements[aInputName].readOnly = true;
    },
    /**
     * <p>check all checkbox</p>
     * eg: <input id="checkHead" onclick="checkAll(this,'key')" type="checkbox"/>
     * 
     * @param {element} aTriggerElement
     * @param {string} aTargetCheckBoxName
     */
    checkAll : function(aTriggerElement, aTargetCheckBoxName){
      for (var i = 0; i < DOC.getElementsByName(aTargetCheckBoxName).length; i++) {
        DOC.getElementsByName(aTargetCheckBoxName)[i].checked = aTriggerElement.checked;
      }
    },
    /**
     * <p>get all checked value(",") by checkbox name</p>
     * 
     * @param {String} aCheckboxName
     * @return string
     */
    getCheckValues : function(aCheckboxName){
      var checkValues = "";
      var elementArr = DOC.getElementsByName(aCheckboxName);
      var size = elementArr.length;
      for (var i = 0; i < size; i++) {
        var element = elementArr[i];
        if (element.checked) {
          checkValues += element.value + ",";
        }
      }
      if (checkValues != "") {
        checkValues = checkValues.substr(0, checkValues.length - 1);
      }
      return checkValues;
    },
    getCheckIds : function(aCheckboxName){
	  var checkNames = "";
	  var elementArr = DOC.getElementsByName(aCheckboxName);
	  var size = elementArr.length;
	  for (var i = 0; i < size; i++) {
	    var element = elementArr[i];
	    if (element.checked) {
	      checkNames += element.id + ",";
	    }
	  }
	  if (checkNames != "") {
		  checkNames = checkNames.substr(0, checkNames.length - 1);
	  }
	  return checkNames;
	},
    /**
     * <p>return true if check one checkbox</p>
     * 
     * @param checkValues
     * @return boolean
     */
    isEdit : function(checkValues) {
      if (jp.isEmpty(checkValues)) {
        alert(I18N.msg_no_sel_edit_record);
        return false;
      } else if (checkValues.indexOf(',') != -1) {
    	alert(I18N.msg_single_edit_record);
        return false;
      } else {
        return true;
      }
    },
    
    /**      
     * <p>set checkbox state</p>
     *     
     * @param {string} aCheckBoxName
     * @param {boolean} aStateBool     
     */   
    setCheckBoxState : function(aCheckBoxName, aStateBool){        
      var elArr = DOC.getElementsByName(name);        
      for (var i = 0; i < elArr.length; i++) {               
        elArr[i].checked = aStateBool;        
      }        
    },
    /**      
     * <p>select checkbox</p>
     * eg: jp.selectCheckbox("ids","1,2")     
     *     
     * @param {string} aElementName      
     * @param {string} aValueStr (,)     
     */
    selectCheckbox : function(aElementName,aValueStr){        
      if ( !jp.isEmpty(aValueStr) ) {
        var checkEl = document.getElementsByName(aElementName);
        var valueArr = aValueStr.split(",");        
        for (var j = 0; j < valueArr.length; j++) {        
          for (var i = 0; i < checkEl.length; i++) {        
            if (checkEl[i].value == valueArr[j]) {        
              checkEl[i].checked = true;    
              break;
            }     
          }    
        }    
      }
    },
    /**      
     * <p>select radio</p>     
     *
     * @param {string} aElementName
     * @param {string} aValueStr     
     */   
    selectRadio : function(aElementName, aValueStr){
      if(!jp.isEmpty(aValueStr)){ 
        var radioEl = DOC.getElementsByName(aElementName);
        for (var i = 0; i < radioEl.length; i++) {
          if(radioEl[i].value == aValueStr) {  
            radioEl[i].checked = "checked";
            break;
          }
        }
      }
    },    
    /**      
     * <p>check select</p>     
     *      
     * @param {string} aElementName
     * @param {string} aValueStr     
     */   
    selectOption : function(aElementName, aValueStr){   
      if (!jp.isEmpty(aValueStr)) {  
        var optionArr = document.getElementsByName(aElementName)[0].options;
        for (var i = 0; i < optionArr.length; i++) {
          if (optionArr[i].value === aValueStr) {
            optionArr[i].selected = true;
            break;
          }        
        }
      }
    },
    //---------------------------------------------- jp Validation ------------
    /**
     * Length should be 9
     * First Character in Id No. should be in Capital
     * Last Character in Id No. should be in Capital
     * First character should be S/T/F/G in Capital Letter
     * Invalid NRIC/FIN/CBC
     * 
     * @param {string} nric
     */
    isNRIC : function(nric) {
      var weight = 0;
      if(nric.length != 9) {   //Check for Length(=9)
        return false;
      }
      firstChar = nric.substring(0,1);
      lastChar = nric.substring(8,9);
      upperFirst = nric.substring(0,1).toUpperCase();
      upperLast  = nric.substring(8,9).toUpperCase();
      if (firstChar!=upperFirst) {
        return false;
      }
      if (lastChar!=upperLast) {
        return false;
      }
      if(firstChar == 'T'  || firstChar == 'G') {
        weight = 4;
      } else if(firstChar == 'S' || firstChar == 'F') { 
    	  
      } else {
        return false;
      }
     
      //Calculate the Summation on NRIC No. digits.
      var chkno = parseInt(nric.substring(1,2),10) * 2;
      chkno = chkno + parseInt(nric.substring(2,3),10) * 7;
      chkno = chkno + parseInt(nric.substring(3,4),10) * 6;
      chkno = chkno + parseInt(nric.substring(4,5),10) * 5;
      chkno = chkno + parseInt(nric.substring(5,6),10) * 4;
      chkno = chkno + parseInt(nric.substring(6,7),10) * 3;
      chkno = chkno + parseInt(nric.substring(7,8),10) * 2;
      chkno = chkno + weight;
     
      //Get the Remainder and minus it from 11.
      chkno = chkno%11;
      chkno = 11 - chkno;
     
      if(jp.isLastCharInNRIC(chkno,firstChar,lastChar) == false) {
        return false;
      }
      return true;
    },
    isLastCharInNRIC : function(chkno,firstChar,lastChar){
      var actualChar ="";
      if(firstChar == 'S' || firstChar == 'T') {
        if(chkno == 1) {
          actualChar ="A";
        } else if(chkno == 2) {
          actualChar ="B";
        } else if(chkno == 3) {
          actualChar ="C";
        } else if(chkno == 4) {
          actualChar ="D";
        } else if(chkno == 5) {
          actualChar ="E";
        } else if(chkno == 6) {
          actualChar ="F";
        } else if(chkno == 7) {
          actualChar ="G";
        } else if(chkno == 8) {
          actualChar ="H";
        } else if(chkno == 9) {
          actualChar ="I";
        } else if(chkno == 10) {
          actualChar ="Z";
        } else if(chkno == 11) {
          actualChar ="J";
        }
      }
      if(firstChar == 'G' || firstChar == 'F') {
        if(chkno == 1) {
          actualChar ="K";
        } else if(chkno == 2) {
          actualChar ="L";
        } else if(chkno == 3) {
          actualChar ="M";
        } else if(chkno == 4) {
          actualChar ="N";
        } else if(chkno == 5) {
          actualChar ="P";
        } else if(chkno == 6) {
          actualChar ="Q";
        } else if(chkno == 7) {
          actualChar ="R";
        } else if(chkno == 8) {
          actualChar ="T";
        } else if(chkno == 9) {
          actualChar ="U";
        } else if(chkno == 10) {
          actualChar ="W";
        } else if(chkno == 11) {
          actualChar ="X";
        }
      } 
      if(actualChar == lastChar) {
        return true;
      } else {
        return false;
      }
    },
    //--------------------------------------------- system method  ---------------------------------------------
    /**
     * <p>creat namespace</p>
     * <pre><code>
       jp.namespace('Company.data');
       jp.namespace('Company', 'Company.data');
       Company.Widget = function() { ... }
       Company.data.CustomStore = function(config) { ... }
       </code></pre>
     *  
     * @param {String} namespace1
     * @param {String} namespace2
     * @param {String} etc
     * @return {Object} The namespace object.
     * @method namespace
     */
    namespace : function(){
      var o, d;
      jp.each(arguments, function(v) {
           d = v.split(".");
           o = window[d[0]] = window[d[0]] || {};
           jp.each(d.slice(1), function(v2){
               o = o[v2] = o[v2] || {};
           });
      });
      return o;
    },
    clone : function(para){
        var rePara = null;
        var type = Object.prototype.toString.call(para);
        if (type.indexOf("Object") > -1) {
            rePara = {};
            for (var key in para) {
                if (para.hasOwnProperty(key)) {
                    rePara[key] = jp.clone(para[key]);
                }
            }
        } else if (type.indexOf("Array") > 0) {
            rePara = [];
            for (var i=0;i<para.length;i++) {
               rePara[rePara.length] =  clone(para[i]);
            }
        } else {
            rePara = para;
        }
        return rePara;
    },
    fixgeometry : function() {
 	   /* Calculate the geometry that our content area should take */
 	   var header = $("#header");
 	   var footer = $("#footer");
 	   var center = $("#center");
 	   var navbar = $(".navbar");
 	   var content = $("#content");
 	   var listview = $("#listview");
 	  
 	   var viewport_height = $(window).height();
 	   var viewport_width = $(window).width();
 	   var center_height = viewport_height - header.outerHeight();
 	   if (footer.outerHeight()!=null) {
 		   center_height = center_height-footer.outerHeight();
 	   }
 	   navbar.width(240);
 	   /* Trim margin/border/padding height */
 	   center_height -= (center.outerHeight() - center.height());
 	   //center.height(center_height);
 	   content.height(center_height);
	   content.width(viewport_width-296);
	   listview.width(viewport_width-298);
	   listview.height(center_height-50);
	   
	   $("#header .title").width(viewport_width-300);
	   //$(window).resize(function() {location.reload();});
 	},
 	autoScroll : function(aSpeed, aContainId, aContainContentId, aContainEmptyId) {
	  var speed = aSpeed;//数值越大，速度越慢
	  var demo2 = document.getElementById(aContainEmptyId);
	  var demo1 = document.getElementById(aContainContentId);
	  var demo = document.getElementById(aContainId);
	  function marqueeLeft() {
		//alert(demo.scrollLeft + "," + demo2.offsetWidth); //220,1240
		if(demo2.offsetWidth-demo.scrollLeft<=0) {
			alert(demo.scrollLeft);
	 	    demo.scrollLeft-=demo1.offsetWidth;
	 	} else {
	 	    demo.scrollLeft++;
	 	}
	  }
 	  var MyMar = setInterval(marqueeLeft,speed);
 	  demo.onmouseover = function() {clearInterval(MyMar);}
 	  demo.onmouseout = function() {MyMar=setInterval(marqueeLeft,speed);}
 	},
 	scrollNews : function(obj){
 	   var $self = obj.find("ul:first"); 
 	   var lineHeight = $self.find("li:first").width(); //获取宽度，向上滚动则需要获取高度.height()
 	   $self.animate({ "marginLeft" : -lineHeight +"px" }, 600 , function(){ //向左滚动，向上滚动则需要改为marginTop,其他方向类似，下同
 	         $self.css({marginLeft:0}).find("li:first").appendTo($self); //appendTo能直接移动元素
 	   })
 	}
  });
  jp.ns = jp.namespace;
})();

jp.ns("jp.util", "jp.lib", "jp.data");

/**
 * <p>extend String</p>
 * 
 * @since 1.0.0
 * @author Ganjianping
 */
jp.applyIf(String, {
    /**
     * <p>format string</p>
     * <pre><code>
       var cls = 'my-class', text = 'my-text';
       var s = String.format('&lt;div class="{0}">{1}&lt;/div>', cls, text); //'<div class="my-class">my-text</div>'
     * </code></pre>
     * 
     * @param {String} aFormatStr 
     * @param {String} value1 The value to replace token {0}
     * @param {String} value2 Etc...
     * @return {String} The formatted string
     * @static
     */
    format: function(aFormatStr) {
        var args = jp.toArray(arguments, 1);
        return aFormatStr.replace(/\{(\d+)\}/g, function(m, i){
            return args[i];
        });
    }
});
/**
 * @class Array
 */
jp.applyIf(Array.prototype, {
  /**
   * Checks whether or not the specified object exists in the array.
   * @param {Object} o The object to check for
   * @param {Number} from (Optional) The index at which to begin the search
   * @return {Number} The index of o in the array (or -1 if it is not found)
   */
  indexOf : function(o, from){
    var len = this.length;
    from = from || 0;
    from += (from < 0) ? len : 0;
    for (; from < len; ++from){
      if(this[from] === o){
          return from;
      }
    }
    return -1;
  },

  /**
   * Removes the specified object from the array.  If the object is not found nothing happens.
   * @param {Object} o The object to remove
   * @return {Array} this array
   */
  remove : function(o){
    var index = this.indexOf(o);
    if(index != -1){
        this.splice(index, 1);
    }
    return this;
  }
});
/**
 * @class Function
 * These functions are available on every Function object (any JavaScript function).
 */
jp.apply(Function.prototype, {
  /**
   * Creates an interceptor function. The passed function is called before the original one. If it returns false,
   * the original one is not called. The resulting function returns the results of the original function.
   * The passed function is called with the parameters of the original function. Example usage:
   * <pre><code>
	 var sayHi = function(name){
	   alert('Hi, ' + name);
	 }
     sayHi('Fred'); // alerts "Hi, Fred"
     var sayHiToFriend = sayHi.createInterceptor(function(name){
       return name == 'Brian';
     });
     sayHiToFriend('Fred');  // no alert
     sayHiToFriend('Brian'); // alerts "Hi, Brian"
     </code></pre>
   * @param {Function} fcn The function to call before the original
   * @param {Object} scope (optional) The scope (<code><b>this</b></code> reference) in which the passed function is executed.
   * <b>If omitted, defaults to the scope in which the original function is called or the browser window.</b>
   * @return {Function} The new function
   */
   createInterceptor : function(fcn, scope){
     var method = this;
     return !jp.isFunction(fcn) ?
	     this :
	     function() {
	         var me = this, args = arguments;
	         fcn.target = me;
	         fcn.method = method;
	         return (fcn.apply(scope || me || window, args) !== false) ?
	                 method.apply(me || window, args) : null;
	     };
  },
  /**
   * Creates a callback that passes arguments[0], arguments[1], arguments[2], ...
   * Call directly on any function. Example: <code>myFunction.createCallback(arg1, arg2)</code>
   * Will create a function that is bound to those 2 args. <b>If a specific scope is required in the
   * callback, use {@link #createDelegate} instead.</b> The function returned by createCallback always
   * executes in the window scope.
   * <p>This method is required when you want to pass arguments to a callback function.  If no arguments
   * are needed, you can simply pass a reference to the function as a callback (e.g., callback: myFn).
   * However, if you tried to pass a function with arguments (e.g., callback: myFn(arg1, arg2)) the function
   * would simply execute immediately when the code is parsed. Example usage:
   * <pre><code>
	var sayHi = function(name){
	    alert('Hi, ' + name);
	}
	new jp.Button({
	    text: 'Say Hi',
	    renderTo: jp.getBody(),
	    handler: sayHi.createCallback('Fred')
	});
	</code></pre>
  * @return {Function} The new function
  */
  createCallback : function(/*args...*/){
    // make args available, in function below
    var args = arguments,
        method = this;
    return function() {
        return method.apply(window, args);
    };
  },
  /**
   * Creates a delegate (callback) that sets the scope to obj.
   * Call directly on any function. Example: <code>this.myFunction.createDelegate(this, [arg1, arg2])</code>
   * Will create a function that is automatically scoped to obj so that the <tt>this</tt> variable inside the
   * callback points to obj. Example usage:
   * <pre><code>
	 var sayHi = function(name){
	    alert('Hi, ' + name + '. You clicked the "' + this.text + '" button.');
	 }
	 var btn = new jp.Button({
       text: 'Say Hi',
       renderTo: jp.getBody()
     });
	 btn.on('click', sayHi.createDelegate(btn, ['Fred']));
     </code></pre>
   * @param {Object} scope (optional) The scope (<code><b>this</b></code> reference) in which the function is executed.
   * <b>If omitted, defaults to the browser window.</b>
   * @param {Array} args (optional) Overrides arguments for the call. (Defaults to the arguments passed by the caller)
   * @param {Boolean/Number} appendArgs (optional) if True args are appended to call args instead of overriding,
   * if a number the args are inserted at the specified position
   * @return {Function} The new function
   */
  createDelegate : function(obj, args, appendArgs){
    var method = this;
    return function() {
      var callArgs = args || arguments;
      if (appendArgs === true){
        callArgs = Array.prototype.slice.call(arguments, 0);
        callArgs = callArgs.concat(args);
      }else if (jp.isNumber(appendArgs)){
        callArgs = Array.prototype.slice.call(arguments, 0); // copy arguments first
        var applyArgs = [appendArgs, 0].concat(args); // create method call params
        Array.prototype.splice.apply(callArgs, applyArgs); // splice them in
      }
      return method.apply(obj || window, callArgs);
    };
  },
  /**
   * Calls this function after the number of millseconds specified, optionally in a specific scope. Example usage:
   * <pre><code>
	 var sayHi = function(name){ alert('Hi, ' + name); }
	 sayHi('Fred');// executes immediately
	 sayHi.defer(2000, this, ['Fred']);// executes after 2 seconds
     </code></pre>
   * @param {Number} millis The number of milliseconds for the setTimeout call (if less than or equal to 0 the function is executed immediately)
   * @param {Object} scope (optional) The scope (<code><b>this</b></code> reference) in which the function is executed.
   * <b>If omitted, defaults to the browser window.</b>
   * @param {Array} args (optional) Overrides arguments for the call. (Defaults to the arguments passed by the caller)
   * @param {Boolean/Number} appendArgs (optional) if True args are appended to call args instead of overriding,
   * if a number the args are inserted at the specified position
   * @return {Number} The timeout id that can be used with clearTimeout
   */
  defer : function(millis, obj, args, appendArgs){
      var fn = this.createDelegate(obj, args, appendArgs);
      if(millis > 0){
          return setTimeout(fn, millis);
      }
      fn();
      return 0;
  }
});

/*! jQuery Placeholder Plugin - v0.7.0 - 2012-12-03
* http://andrew-jones.com/jquery-placeholder-plugin
* Copyright (c) 2012 Andrew Jones; Licensed MIT */

(function ($) {
  "use strict";

  $.extend({
    placeholder: {
      settings: {
        focusClass: 'placeholderFocus',
        activeClass: 'placeholder',
        overrideSupport: false,
        preventRefreshIssues: true
      }
    }

  });

  // check browser support for placeholder
  $.support.placeholder = 'placeholder' in document.createElement('input');

  // Replace the val function to never return placeholders
  $.fn.plVal = $.fn.val;
  $.fn.val = function (value) {
    if (typeof value === 'undefined') {
      return $.fn.plVal.call(this);
    } else {
      var el = $(this[0]);
      var currentValue = el.plVal();
      var returnValue = $(this).plVal(value);
      if (el.hasClass($.placeholder.settings.activeClass) && currentValue === el.attr('placeholder')) {
        el.removeClass($.placeholder.settings.activeClass);
        return returnValue;
      }

      if (el.hasClass($.placeholder.settings.activeClass) && el.plVal() === el.attr('placeholder')) {
        return '';
      }

      return $.fn.plVal.call(this, value);
    }
  };

  // Clear placeholder values upon page reload
  $(window).bind('beforeunload.placeholder', function () {
    var els = $('input.' + $.placeholder.settings.activeClass);
    if (els.length > 0) {
      els.val('').attr('autocomplete', 'off');
    }
  });


  // plugin code
  $.fn.placeholder = function (opts) {
    opts = $.extend({}, $.placeholder.settings, opts);

    // we don't have to do anything if the browser supports placeholder
    if (!opts.overrideSupport && $.support.placeholder) {
      return this;
    }

    return this.each(function () {
      var $el = $(this);

      // skip if we do not have the placeholder attribute
      if (!$el.is('[placeholder]')) {
        return;
      }

      // we cannot do password fields, but supported browsers can
      if ($el.is(':password')) {
        return;
      }

      // Prevent values from being reapplied on refresh
      if (opts.preventRefreshIssues) {
        $el.attr('autocomplete', 'off');
      }

      $el.bind('focus.placeholder', function () {
        var $el = $(this);
        if (this.value === $el.attr('placeholder') && $el.hasClass(opts.activeClass)) {
          $el.val('').removeClass(opts.activeClass).addClass(opts.focusClass);
        }
      });

      $el.bind('blur.placeholder', function () {
        var $el = $(this);

        $el.removeClass(opts.focusClass);

        if (this.value === '') {
          $el.val($el.attr('placeholder')).addClass(opts.activeClass);
        }
      });

      $el.triggerHandler('blur');

      // Prevent incorrect form values being posted
      $el.parents('form').submit(function () {
        $el.triggerHandler('focus.placeholder');
      });

    });
  };
}(jQuery));