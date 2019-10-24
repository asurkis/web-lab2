const toDeleteAll = document.getElementById('remove-check-all');
const toDeleteList = document.querySelectorAll('input[name^=d]');

if (toDeleteAll) {
    toDeleteAll.addEventListener('input', checkAll);
    toDeleteList.forEach(cb => cb.addEventListener('input', markChecked));
}

function checkAll() {
    toDeleteList.forEach(cb => cb.checked = toDeleteAll.checked);
}

function markChecked() {
    let allChecked = true;
    let allUnchecked = true;

    for (const cb of toDeleteList) {
        allChecked &= cb.checked;
        allUnchecked &= !cb.checked;
    }

    if (allChecked) {
        toDeleteAll.checked = true;
    }

    if (allUnchecked) {
        toDeleteAll.checked = false;
    }
}

const keyList = ['x', 'y', 'r'];
const areaCheckSubmit = document.getElementById('area-check-submit');
const inputs = {};
const markers = {};

for (const key of keyList) {
    inputs[key] = document.querySelectorAll(`input[name^=${key}]`);
    inputs[key].forEach(e => e.addEventListener('input', validateInputs));
    markers[key] = document.getElementById('invalid-' + key);
}

function validateX() {
    for (const input of inputs.x) {
        if (input.checked) {
            return true;
        }
    }
    return false;
}

function validateY() {
    return inputs.y[0].value.match(/^(-[0-3]|[0-5])([.,]\d*)?$/) !== null;
}

function validateR() {
    for (const input of inputs.r) {
        if (input.checked) {
            return true;
        }
    }
    return false;
}

function validateInputs() {
    const xValid = validateX(); markValid('x', xValid);
    const yValid = validateY(); markValid('y', yValid);
    const rValid = validateR(); markValid('r', rValid);

    areaCheckSubmit.disabled = xValid && yValid && rValid ? '' : 'disabled';
}

function markValid(key, isValid) {
    markers[key].style.visibility = isValid ? 'hidden' : 'visible';
}

const svgImage = document.querySelector('svg');

svgImage.addEventListener('click', onSvgClick);
function onSvgClick(e) {
    if (!validateR()) {
        markValid('r', false);
        return;
    }

    const rect = svgImage.getBoundingClientRect();
    const x = (e.x - rect.left - 300) / 50;
    const y = (e.y - rect.top - 300) / (-50);

    let request = `${window.location.href.match(/^[^?]*/)[0]}?x=${x}&y=${y}`;
    for (const input of inputs.r) {
        if (input.checked) {
            request += '&r=' + input.value;
        }
    }

    window.location.href = request;
}

inputs.r.forEach(e => e.addEventListener('input', onRadiusInput));
async function onRadiusInput() {
    let request = `${window.location.href.match(/^[^?]*/)[0]}?p`;
    for (const input of inputs.r) {
        if (input.checked) {
            request += '&r=' + input.value;
        }
    }

    const response = await fetch(request);
    if (response.ok) {
        svgImage.innerHTML = await response.text();
        mouseCircle = document.getElementById('mouse-circle');
    }
}

let mouseCircle = document.getElementById('mouse-circle');

svgImage.addEventListener('mousemove', svgHover);
function svgHover(e) {
    const rect = svgImage.getBoundingClientRect();
    mouseCircle.cx.baseVal.value = e.x - rect.left;
    mouseCircle.cy.baseVal.value = e.y - rect.top;
}
