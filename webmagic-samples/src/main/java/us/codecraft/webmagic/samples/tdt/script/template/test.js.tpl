let classes = ${content};

let notExist = {};
let superClass = {};
let classesLen = classes.length;

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
            }
        } else {
            if (!curObj) {
                notExist[curClass.name] = 1;
            }
        }
    }
}

console.log(superClass);