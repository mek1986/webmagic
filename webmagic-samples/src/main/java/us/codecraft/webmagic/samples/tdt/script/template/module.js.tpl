;!(function (win, $) {
    /**
     * @version ${content.version}
     * @create_time ${content.addTime?string["yyyy-MM-dd HH:mm:ss"]}
     */

    "use strict";

    <#if content.classList??>
    <#list content.classList as classItem>
    <#if !classItem.parent??>
    /**
     * ${classItem.classDesc}<p>
     * @crawl_url ${classItem.fromUrl}
     */
    class ${classItem.className?replace(".", "")} {
        _obj = null;

        getObj(){
            return this._obj;                
        }
        
        setObj(obj){
            if(this._obj!=null){                
                throw "can not change obj";                              
            }
            
            if(!(obj instanceof T.${classItem.className})){                
                throw "obj type is not match";                              
            }
            
            this._obj = obj;
        }
        
        <#if classItem.eventList??>
        <#-- 事件列表 -->
        static events = {
            <#list classItem.eventList as detail>
            //${detail.eventDesc} [回调参数：${detail.params}]
            ${detail.eventName}: '${detail.eventName}',
            </#list>
        };

        </#if>
        <#if classItem.optionDetailList??>
        <#-- options -->
        static options = {
            <#list classItem.optionDetailList as detail>
            //${detail.attrDesc} [${detail.attrType}]
            ${detail.attrName}: <#if detail.attrDefaultValue=="无">null<#elseif detail.attrDefaultValue?contains("(")>new T.${detail.attrDefaultValue}<#elseif detail.attrDefaultValue=="*">new T.Icon.Default()<#else>${detail.attrDefaultValue}</#if>,
            </#list>
        };

        </#if>
        <#if classItem.methodList??>
        <#list classItem.methodList as methodItem>
        /**
         * ${methodItem.methodDesc}<p>
         <#if methodItem.paramsName??>
         <#list methodItem.paramsName as p>
         * @param ${p} ${methodItem.params[p].desc} [${methodItem.params[p].type},<#if methodItem.params[p].need>必传<#else>非必传</#if>]<p>
         </#list>
         </#if>
         <#if methodItem.returnType!="none">
         * @returns {${methodItem.returnType}}
         </#if>
         */
        <#if methodItem.methodName == classItem.className>constructor${methodItem.paramsBody}<#else>${methodItem.methodCall}</#if> {
            <#if methodItem.methodName == classItem.className>
            <#-- 构造函数 -->
            this._obj = new T.${classItem.className}${methodItem.paramsBody};

            <#else>
            <#-- 普通函数 -->
            if(!this._obj){
                throw "not initialize correct";
            }
            return this._obj.${methodItem.methodCall};
            </#if>
        }

        </#list>
        </#if>

        <#if !methodHelp[classItem.className+"-"+classItem.className]??>
        /**
         * 构造函数
         */
        constructor() {
            this._obj = null;
        }
        </#if>

        <#if classItem.eventList??&&!methodHelp[classItem.className+"-addEventListener"]??>
        <#-- 事件 -->
        /**
         * 添加事件回调
         * @param eventName 事件名称
         * @param callback 事件回调
         */
        addEventListener(eventName, callback) {
            if(!this._obj){
                throw "not initialize correct";
            }
            this._obj.addEventListener(eventName, callback);
        }

        /**
         * 删除事件回调
         * @param eventName 事件名称
         * @param callback 事件回调
         */
        removeEventListener(eventName, callback) {
            if(!this._obj){
                throw "not initialize correct";
            }
            this._obj.removeEventListener(eventName, callback);
        }
        </#if>
    }
    /**
     * ${classItem.classDesc}<p>
     */
    win.tdt.clz.${classItem.className?replace(".", "")} = ${classItem.className?replace(".", "")};

    </#if>
    </#list>
    </#if>

    <#if content.classList??>
    <#list content.classList as classItem>
    <#if classItem.parent??>
    /**
     * ${classItem.classDesc}<p>
     * @crawl_url ${classItem.fromUrl}
     * @version ${content.version}
     * @create_time ${classItem.addTime?string["yyyy-MM-dd HH:mm:ss"]}
     */
    class ${classItem.className?replace(".", "")} extends ${classItem.parent?replace(".", "")} {
        _obj = null;

        getObj(){
            return this._obj;
        }

        setObj(obj){
            if(this._obj!=null){
                throw "can not change obj";
            }

            if(!(obj instanceof T.${classItem.className})){
                throw "obj type is not match";
            }

            this._obj = obj;
        }

        <#if classItem.eventList??>
        <#-- 事件列表 -->
        static events = {
            <#list classItem.eventList as detail>
            //${detail.eventDesc} [回调参数：${detail.params}]
            ${detail.eventName}: '${detail.eventName}',
            </#list>
        };

        </#if>
        <#if classItem.optionDetailList??>
        <#-- options -->
        static options = {
            <#list classItem.optionDetailList as detail>
            //${detail.attrDesc} [${detail.attrType}]
            ${detail.attrName}: <#if detail.attrDefaultValue=="无">null<#elseif detail.attrDefaultValue?contains("(")>new T.${detail.attrDefaultValue}<#elseif detail.attrDefaultValue=="*">new T.Icon.Default()<#else>${detail.attrDefaultValue}</#if>,
            </#list>
        };

        </#if>
        <#if classItem.methodList??>
        <#list classItem.methodList as methodItem>
        /**
         * ${methodItem.methodDesc}<p>
         <#if methodItem.paramsName??>
         <#list methodItem.paramsName as p>
         * @param ${p} ${methodItem.params[p].desc} [${methodItem.params[p].type},<#if methodItem.params[p].need>必传<#else>非必传</#if>]<p>
         </#list>
         </#if>
         <#if methodItem.returnType!="none">
         * @returns {${methodItem.returnType}}
         </#if>
         */
        <#if methodItem.methodName == classItem.className>constructor${methodItem.paramsBody}<#else>${methodItem.methodCall}</#if> {
            <#if methodItem.methodName == classItem.className>
            <#-- 构造函数 -->
            <#if methodHelp[classItem.parent+"-"+classItem.parent]??>
            super${methodHelp[classItem.parent+"-"+classItem.parent].paramsBody};
            <#else>
            super();
            </#if>

            this._obj = new T.${classItem.className}${methodItem.paramsBody};
            <#else>
            <#-- 普通函数 -->
            if(!this._obj){
                throw "not initialize correct";
            }
            return this._obj.${methodItem.methodCall};
            </#if>
        }

        </#list>
        </#if>

        <#if !methodHelp[classItem.className+"-"+classItem.className]??>
        /**
         * 构造函数
         */
        constructor() {
            super();
            this._obj = null;
        }
        </#if>

        <#if classItem.eventList??&&!methodHelp[classItem.className+"-addEventListener"]??>
        <#-- 事件 -->
        /**
         * 添加事件回调
         * @param eventName 事件名称
         * @param callback 事件回调
         */
        addEventListener(eventName, callback) {
            if(!this._obj){
                throw "not initialize correct";
            }
            this._obj.addEventListener(eventName, callback);
        }

        /**
         * 删除事件回调
         * @param eventName 事件名称
         * @param callback 事件回调
         */
        removeEventListener(eventName, callback) {
            if(!this._obj){
                throw "not initialize correct";
            }
            this._obj.removeEventListener(eventName, callback);
        }
        </#if>
    }
    /**
     * ${classItem.classDesc}<p>
     */
    win.tdt.clz.${classItem.className?replace(".", "")} = ${classItem.className?replace(".", "")};

    </#if>
    </#list>
    </#if>

    <#if content.enums??>
    <#assign keys = content.enums?keys>
    <#list keys as key>
    win.tdt.enum.${key?replace(".","")} = {
        <#list content.enums[key] as enumItem>
        //${enumItem.constValueDesc}
        ${enumItem.constValue}: ${enumItem.constValue},
        </#list>
    };

    </#list>
    </#if>
})(window, jQuery);