let classes = ${content};

let notExist = {};
let superClass = {};
let extendInfos = {};
let classesLen = classes.length;
let outJson = {};

for (let i = 0; i < classesLen; i++) {
    let curClass = classes[i];
    let curObj = eval(curClass.obj);
    for (let j = 0; j < classesLen; j++) {
        if (i === j) {
            continue;
        }

        let compareClass = classes[j];
        let compareObj = eval(compareClass.obj);

        if (curObj && compareObj) {
            if (curObj.prototype instanceof compareObj) {
                if (!(compareClass.name in superClass)) {
                    superClass[compareClass.name] = [];
                }

                superClass[compareClass.name].push(curClass.name);
                extendInfos[curClass.name] = compareClass.name;
            }
        } else {
            if (!curObj) {
                notExist[curClass.name] = 1;
            }
        }
    }
}

outJson['super']= superClass;
outJson['child']= extendInfos;
outJson['notExist']= notExist;

document.getElementById('content').innerHtml = JSON.stringify(outJson);