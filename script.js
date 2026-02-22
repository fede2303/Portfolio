// 1. Selezioniamo gli elementi dal DOM (la struttura della pagina)
const toggleButton = document.getElementById('theme-toggle');
const body = document.body;

// 2. Aggiungiamo un "ascoltatore di eventi" (Event Listener)
// Quando l'utente clicca ("click") sul bottone, eseguiamo questa funzione anonima
toggleButton.addEventListener('click', () => {
    
    // 3. Aggiungiamo o togliamo la classe 'light-mode' al body
    body.classList.toggle('light-mode');

    // 4. Cambiamo l'icona del bottone in base al tema
    if (body.classList.contains('light-mode')) {
        toggleButton.textContent = '🌙'; // Se è chiaro, mostra la luna
    } else {
        toggleButton.textContent = '☀️'; // Se è scuro, mostra il sole
    }
});

// DEBUG per ingegneri:
console.log("Script caricato correttamente! Controlla la console del browser (F12)");

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