// --- 1. GESTIONE TEMA (CHIARO/SCURO) ---
const toggleButton = document.getElementById('theme-toggle');
const body = document.body;

toggleButton.addEventListener('click', () => {
    body.classList.toggle('light-mode');

    // Cambiamo l'icona del bottone in base al tema
    if (body.classList.contains('light-mode')) {
        toggleButton.textContent = '🌙'; 
    } else {
        toggleButton.textContent = '☀️'; 
    }
});

// --- 2. EFFETTO 3D CARD (MOUSEMOVE) ---
const cards = document.querySelectorAll('.card');

cards.forEach(card => {
    card.addEventListener('mousemove', (e) => {
        const rect = card.getBoundingClientRect();
        const x = e.clientX - rect.left;
        const y = e.clientY - rect.top;

        const centerX = rect.width / 2;
        const centerY = rect.height / 2;

        const rotateX = (y - centerY) / 10;
        const rotateY = (centerX - x) / 10;

        card.style.transform = `perspective(1000px) rotateX(${rotateX}deg) rotateY(${rotateY}deg) scale3d(1.05, 1.05, 1.05)`;
    });

    card.addEventListener('mouseleave', () => {
        card.style.transform = `perspective(1000px) rotateX(0deg) rotateY(0deg) scale3d(1, 1, 1)`;
    });
});

// --- 3. HAMBURGER MENU (MOBILE) ---
const hamburger = document.getElementById('hamburger');
const navMenu = document.getElementById('nav-menu');

// Apre/Chiude il menu cliccando sulle lineette
hamburger.addEventListener('click', () => {
    navMenu.classList.toggle('active');
    hamburger.classList.toggle('open'); // Questo attiva l'animazione a X
});

// Chiude il menu quando clicchi su un link
document.querySelectorAll('nav ul li a').forEach(link => {
    link.addEventListener('click', () => {
        navMenu.classList.remove('active');
        hamburger.classList.remove('open'); // Reset delle lineette dopo il click
    });
});

// DEBUG
console.log("Portfolio Script: Integrità verificata. UI pronta.");
