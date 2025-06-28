const button = document.getElementById('menu-button');
const menu = document.getElementById('menu');

button.addEventListener('click', () => {
    const isExpanded = button.getAttribute('aria-expanded') === 'true';
    button.setAttribute('aria-expanded', String(!isExpanded));
    menu.classList.toggle('show');
});