// Espera a que cargue todo el HTML
document.addEventListener("DOMContentLoaded", () => {

    // =====================================================
    // BUSCADOR DEL CATÁLOGO
    // =====================================================

    const buscador = document.getElementById("buscador");
    const mensajeSinResultados = document.getElementById("sin-resultados");
    const plantas = document.querySelectorAll(".tarjeta-planta");

    // Verifica si existe el buscador
    if (buscador) {

        buscador.addEventListener("keyup", (e) => {

            // Texto escrito por el usuario
            const texto = e.target.value.toLowerCase().trim();

            let encontrados = 0;

            // Recorremos todas las tarjetas
            plantas.forEach(planta => {

                // Obtener nombre de la planta
                const nombreElemento = planta.querySelector(".card-title");

                // Seguridad extra
                if (!nombreElemento) return;

                const nombre = nombreElemento.textContent.toLowerCase();

                // Comparación
                if (nombre.includes(texto)) {

                    planta.style.display = "block";
                    encontrados++;

                } else {

                    planta.style.display = "none";

                }

            });

            // Mostrar mensaje si no hay resultados
            if (mensajeSinResultados) {

                if (encontrados === 0 && texto !== "") {

                    mensajeSinResultados.classList.remove("d-none");

                } else {

                    mensajeSinResultados.classList.add("d-none");

                }

            }

        });

    }

    // =====================================================
    // BOTONES DE WHATSAPP
    // =====================================================

    const numeroWhatsapp = "593979084293";

    const botonesWhatsapp = document.querySelectorAll(".btn-whatsapp");

    botonesWhatsapp.forEach(boton => {

        boton.addEventListener("click", (e) => {

            e.preventDefault();

            // Obtener datos desde data-attributes
            const nombrePlanta = boton.dataset.nombre || "una planta";
            const precioPlanta = boton.dataset.precio || "";

            // Crear mensaje
            let mensaje = `Hola! Quiero comprar la ${nombrePlanta}`;

            // Agrega precio si existe
            if (precioPlanta !== "") {
                mensaje += ` (${precioPlanta})`;
            }

            mensaje += ". ¿Está disponible?";

            // Codificar URL
            const mensajeCodificado = encodeURIComponent(mensaje);

            // URL final
            const urlWhatsapp =
                `https://wa.me/${numeroWhatsapp}?text=${mensajeCodificado}`;

            // Abrir WhatsApp
            window.open(urlWhatsapp, "_blank");

        });

    });

});

/* ==========================================================================
   DETECTOR AUTOMÁTICO DE PÁGINA ACTUAL (SUBRAYADO DINÁMICO)
   ========================================================================== */
document.addEventListener("DOMContentLoaded", function () {
    // Obtiene la ruta actual del navegador (por ejemplo: /contacto o /catalogo)
    const currentPath = window.location.pathname;
    
    // Busca todos los enlaces dentro de nuestra lista de navegación
    const navLinks = document.querySelectorAll("#main-navbar-links .nav-link");

    navLinks.forEach(link => {
        // Obtiene la ruta a la que apunta el enlace en el HTML
        const linkPath = link.getAttribute("href");

        // Si la ruta coincide exactamente o si la URL empieza con esa subruta
        if (currentPath === linkPath || (linkPath !== '/' && currentPath.startsWith(linkPath))) {
            link.classList.add("active");
        } else {
            link.classList.remove("active");
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    // Selecciona todas las imágenes que configuramos como cliqueables
    const imagenes = document.querySelectorAll(".img-cliqueable");
    const imagenModalGrande = document.getElementById("imagenModalGrande");
    
    // Inicializa el modal usando la librería nativa de Bootstrap 5
    const miModal = new bootstrap.Modal(document.getElementById("modalImagen"));

    imagenes.forEach(img => {
        img.addEventListener("click", function () {
            // Obtiene la URL guardada en el atributo personalizado
            const urlImagen = this.getAttribute("data-img-grande");
            
            // Asigna la URL al contenedor del modal
            imagenModalGrande.src = urlImagen;
            
            // Muestra la ventana emergente en pantalla
            miModal.show();
        });
    });
});
