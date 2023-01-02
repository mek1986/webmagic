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
        <#if classItem.eventList??>
        <#-- 事件列表 -->
        static events = {
            <#list classItem.eventList as detail>
            //${detail.eventDesc} [回调参数：${detail.params}]
            ${detail.eventName}: '${detail.eventName}',
            </#list>
        }

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
            this.obj = new T.${classItem.className}${methodItem.paramsBody};

            <#else>
            <#-- 普通函数 -->
            if(!this.obj){
                throw "not initialize correct";
            }
            return this.obj.${methodItem.methodCall};
            </#if>
        }

        </#list>
        </#if>

        <#if !methodHelp[classItem.className]??>
        /**
         * 构造函数
         */
        constructor() {
            this.obj = null;
        }
        </#if>

        <#if content.eventList??>
        <#-- 事件 -->
        /**
         * 添加事件回调
         * @param eventName 事件名称
         * @param callback 事件回调
         */
        addEventListener(eventName, callback) {
            if(!this.obj){
                throw "not initialize correct";
            }
            this.obj.addEventListener(eventName, callback);
        }

        /**
         * 删除事件回调
         * @param eventName 事件名称
         * @param callback 事件回调
         */
        removeEventListener(eventName, callback) {
            if(!this.obj){
                throw "not initialize correct";
            }
            this.obj.removeEventListener(eventName, callback);
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
        <#if classItem.eventList??>
        <#-- 事件列表 -->
        static events = {
            <#list classItem.eventList as detail>
            //${detail.eventDesc} [回调参数：${detail.params}]
            ${detail.eventName}: '${detail.eventName}',
            </#list>
        }

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
            <#if methodHelp[classItem.parent]??>
            super${methodHelp[classItem.parent].paramsBody};
            <#else>
            super()
            </#if>

            this.obj = new T.${classItem.className}${methodItem.paramsBody};
            <#else>
            <#-- 普通函数 -->
            if(!this.obj){
                throw "not initialize correct";
            }
            return this.obj.${methodItem.methodCall};
            </#if>
        }

        </#list>
        </#if>

        <#if !methodHelp[classItem.className]??>
        /**
         * 构造函数
         */
        constructor() {
            super();
            this.obj = null;
        }
        </#if>

        <#if content.eventList??>
        <#-- 事件 -->
        /**
         * 添加事件回调
         * @param eventName 事件名称
         * @param callback 事件回调
         */
        addEventListener(eventName, callback) {
            if(!this.obj){
                throw "not initialize correct";
            }
            this.obj.addEventListener(eventName, callback);
        }

        /**
         * 删除事件回调
         * @param eventName 事件名称
         * @param callback 事件回调
         */
        removeEventListener(eventName, callback) {
            if(!this.obj){
                throw "not initialize correct";
            }
            this.obj.removeEventListener(eventName, callback);
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