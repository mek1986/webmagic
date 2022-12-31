;!(function (win, $) {
    /**
     * ${content.moduleName}
     * @version ${content.version}
     * @create_time ${classItem.addTime}
     */

    "use strict";

    <#if content.classList??>
    <#list content.classList as classItem>
    <#if classItem.parent==null>
    /**
     * ${classItem.classDesc}<br>
     * @crawl_url ${classItem.fromUrl}
     */
    class ${classItem.className} {
        <#list classItem.methodList as methodItem>
        /**
         * ${methodItem.methodDesc}<br>
         <#if methodItem.paramsName??>
         <#list methodItem.paramsName as p>
         * @param ${p} ${methodItem.params[p].desc} [${methodItem.params[p].type},<#if methodItem.params[p].need>必传<#else>非必传</#if>]<br>
         </#list>
         </#if>
         <#if methodItem.returnType!="none">
         * @returns {${methodItem.returnType}}
         </#if>
         */
        <#if methodItem.methodName == classItem.className>constructor${methodItem.paramsBody}<#else>${methodItem.methodCall}</#if> {
            <#if methodItem.methodName == classItem.className>
            <!-- 构造函数 -->
            this.obj = new T.${methodItem.methodCall};
            Object.defineProperty(this, 'obj', {
                get() {
                    return obj;
                },
                set(v) {
                    if(!(v instanceof T.${classItem.className})){
                        throw 'type not match!';
                    }

                    obj = v;
                },
                enumerable: true,
                configurable: true
            });

            <#if classItem.optionDetailList??>
            <!-- options -->
            Object.defineProperty(this, 'options', {
                get() {
                    return {
                        <#list classItem.optionDetailList as detail>
                        //${detail.attrDesc} [${detail.attrType}]
                        ${detail.attrName}: <#if detail.attrDefaultValue=="无">null<#else>${detail.attrDefaultValuel}</#if>,
                        </#list>
                    }
                },
                enumerable: true,
                configurable: false
            });
            </#if>

            <#if classItem.eventList??>
            <!-- 事件列表 -->
            Object.defineProperty(this, 'events', {
                get() {
                    return {
                        <#list classItem.eventList as detail>
                        //${detail.eventDesc} [回调参数：${detail.params}]
                        ${detail.eventName}: '${detail.eventName}',
                        </#list>
                    }
                },
                enumerable: true,
                configurable: false
            });
            </#if>
            <#else>
            <!-- 普通函数 -->
            if(!this.obj){
                throw "not initialize correct";
            }
            return this.obj.${methodItem.methodCall};
            </#if>
        }

        </#list>

        <#if !methodHelp[classItem.className]??>
        /**
         * 构造函数<br>
         */
        constructor() {
            this.obj = null;
            Object.defineProperty(this, 'obj', {
                get() {
                    return obj;
                },
                set(v) {
                    if(!(v instanceof T.${classItem.className})){
                        throw 'type not match!';
                    }

                    obj = v;
                },
                enumerable: true,
                configurable: true
            });

            <#if classItem.optionDetailList??>
            <!-- options -->
            Object.defineProperty(this, 'options', {
                get() {
                    return {
                        <#list classItem.optionDetailList as detail>
                        //${detail.attrDesc} [${detail.attrType}]
                        ${detail.attrName}: <#if detail.attrDefaultValue=="无">null<#else>${detail.attrDefaultValuel}</#if>,
                        </#list>
                    }
                },
                enumerable: true,
                configurable: false
            });
            </#if>

            <#if classItem.eventList??>
            <!-- 事件列表 -->
            Object.defineProperty(this, 'events', {
                get() {
                    return {
                        <#list classItem.eventList as detail>
                        //${detail.eventDesc} [回调参数：${detail.params}]
                        ${detail.eventName}: '${detail.eventName}',
                        </#list>
                    }
                },
                enumerable: true,
                configurable: false
            });
            </#if>
        }
        </#if>

        <#if content.eventList??>
        <!-- 事件 -->
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
    win.tdt.clz.${classItem.className} = ${classItem.className};
    </#if>
    </#list>
    </#if>

    <#if content.classList??>
    <#list content.classList as classItem>
    <#if classItem.parent!=null>
    /**
     * ${classItem.classDesc}<br>
     * @crawl_url ${classItem.fromUrl}
     * @version ${content.version}
     * @create_time ${classItem.addTime}
     */
    class ${classItem.className} extents win.tdt.clz.${classItem.parent} {
        <#list classItem.methodList as methodItem>
        /**
         * ${methodItem.methodDesc}<br>
         <#if methodItem.paramsName??>
         <#list methodItem.paramsName as p>
         * @param ${p} ${methodItem.params[p].desc} [${methodItem.params[p].type},<#if methodItem.params[p].need>必传<#else>非必传</#if>]<br>
         </#list>
         </#if>
         <#if methodItem.returnType!="none">
         * @returns {${methodItem.returnType}}
         </#if>
         */
        <#if methodItem.methodName == classItem.className>constructor${methodItem.paramsBody}<#else>${methodItem.methodCall}</#if> {
            <#if methodItem.methodName == classItem.className>
            <!-- 构造函数 -->
            this.obj = new T.${methodItem.methodCall};
            Object.defineProperty(this, 'obj', {
                get() {
                    return obj;
                },
                set(v) {
                    if(!(v instanceof T.${classItem.className})){
                        throw 'type not match!';
                    }

                    obj = v;
                },
                enumerable: true,
                configurable: true
            });

            <#if classItem.optionDetailList??>
            <!-- options -->
            Object.defineProperty(this, 'options', {
                get() {
                    return {
                        <#list classItem.optionDetailList as detail>
                        //${detail.attrDesc} [${detail.attrType}]
                        ${detail.attrName}: <#if detail.attrDefaultValue=="无">null<#else>${detail.attrDefaultValuel}</#if>,
                        </#list>
                    }
                },
                enumerable: true,
                configurable: false
            });
            </#if>

            <#if classItem.eventList??>
            <!-- 事件列表 -->
            Object.defineProperty(this, 'events', {
                get() {
                    return {
                        <#list classItem.eventList as detail>
                        //${detail.eventDesc} [回调参数：${detail.params}]
                        ${detail.eventName}: '${detail.eventName}',
                        </#list>
                    }
                },
                enumerable: true,
                configurable: false
            });
            </#if>
            <#else>
            <!-- 普通函数 -->
            if(!this.obj){
                throw "not initialize correct";
            }
            return this.obj.${methodItem.methodCall};
            </#if>
        }

        </#list>

        <#if !methodHelp[classItem.className]??>
        /**
         * 构造函数<br>
         */
        constructor() {
            this.obj = null;
            Object.defineProperty(this, 'obj', {
                get() {
                    return obj;
                },
                set(v) {
                    if(!(v instanceof T.${classItem.className})){
                        throw 'type not match!';
                    }

                    obj = v;
                },
                enumerable: true,
                configurable: true
            });

            <#if classItem.optionDetailList??>
            <!-- options -->
            Object.defineProperty(this, 'options', {
                get() {
                    return {
                        <#list classItem.optionDetailList as detail>
                        //${detail.attrDesc} [${detail.attrType}]
                        ${detail.attrName}: <#if detail.attrDefaultValue=="无">null<#else>${detail.attrDefaultValuel}</#if>,
                        </#list>
                    }
                },
                enumerable: true,
                configurable: false
            });
            </#if>

            <#if classItem.eventList??>
            <!-- 事件列表 -->
            Object.defineProperty(this, 'events', {
                get() {
                    return {
                        <#list classItem.eventList as detail>
                        //${detail.eventDesc} [回调参数：${detail.params}]
                        ${detail.eventName}: '${detail.eventName}',
                        </#list>
                    }
                },
                enumerable: true,
                configurable: false
            });
            </#if>
        }
        </#if>

        <#if content.eventList??>
        <!-- 事件 -->
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
    win.tdt.clz.${classItem.className} = ${classItem.className};
    </#if>
    </#list>
    </#if>

    <#if content.enums??>
    <#assing keys = content.enums?keys>
    <#list keys as key>
    <#list content.enums[key] as enumItem>
    win.tdt.enum.${key} = {
        //${enumItem.constValueDesc}
        ${enumItem.constValue}: ${enumItem.constValue},
    };

    </#list>
    </#list>
    </#if>
})(window, jQuery);