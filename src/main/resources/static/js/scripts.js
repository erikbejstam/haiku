const menuButton = document.getElementById('menu-button');
const menu = document.getElementById('menu');

const upArrow = document.getElementById('up-arrow');
const downArrow = document.getElementById('down-arrow');
const haikus = document.querySelectorAll('.haiku');
let currentIndex = 0;

// Toggle menu items

menuButton.addEventListener('click', () => {
    const isExpanded = menuButton.getAttribute('aria-expanded') === 'true';
    menuButton.setAttribute('aria-expanded', String(!isExpanded));
    menu.classList.toggle('show');
});

// Navigate to next/previous haiku

function showHaiku(index) {
    haikus[currentIndex].classList.add('hidden');
    currentIndex =  (index + haikus.length) % haikus.length;
    haikus[currentIndex].classList.remove('hidden');
}

upArrow.addEventListener('click', () => showHaiku(currentIndex + 1));
downArrow.addEventListener('click', () => showHaiku(currentIndex - 1));

