
// ADMIN

function startGame() {
    fetch(`${BASE_URL}/admin/startGame`, {
        method: "POST"
    })
    .then(res => res.text())
    .then(msg => alert(msg));
}

// USER
function revealName() {
    const phone = document.getElementById("userPhone").value;

    fetch(`${BASE_URL}/game/reveal?phone=${phone}`, {
        method: "POST"
    })
    .then(res => res.text())
    .then(result => {
        document.getElementById("result").innerText = result;
    });
}

function resetGame() {
    fetch(`${BASE_URL}/admin/resetGame`, {
        method: "POST"
    })
    .then(res => res.text())
    .then(msg => alert(msg));
}

function fullResetGame() {
    const confirmReset = confirm(
        " This will delete ALL players and assignments. Are you sure?"
    );

    if (!confirmReset) return;

    fetch(`${BASE_URL}/admin/fullResetGame`, {
        method: "POST"
    })
    .then(res => res.text())
    .then(msg => {
        alert(msg);
        location.reload(); // 🔄 refresh page
    });
}


function addPlayer() {
    const name = document.getElementById("name").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const msg = document.getElementById("adminMsg");

    // Name validation
    if (name === "") {
        msg.innerText = "❌ Name is required";
        msg.style.color = "blue";
        return;
    }

    // Phone validation (10 digits)
    const phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(phone)) {
        msg.innerText = " Phone number must be exactly 10 digits";
        msg.style.color = "blue";
        return;
    }

    fetch(`${BASE_URL}/admin/addPlayer`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ name, phone })
    })
    .then(res => res.text())
    .then(response => {
        msg.innerText = response;
        msg.style.color = response.includes("success") ? "lightgreen" : "red";

        if (response.includes("success")) {
            document.getElementById("name").value = "";
            document.getElementById("phone").value = "";
            loadPlayers(); // refresh player list
        }
    });
}

function loadPlayers() {
    fetch(`${BASE_URL}/admin/players`)
        .then(res => res.json())
        .then(players => {
            const tbody = document.querySelector("#playerTable tbody");
            tbody.innerHTML = "";

            if (players.length === 0) {
                tbody.innerHTML = `<tr><td colspan="4">No players added</td></tr>`;
                return;
            }

            players.forEach((player, index) => {
                const row = `
                    <tr>
                        <td>${index + 1}</td>
                        <td>${player.name}</td>
                        <td>${player.phone}</td>
                        <td>
                            <button onclick="deletePlayer(${player.id})">
                                X
                            </button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += row;
            });
        });
}


function deletePlayer(id) {
    const confirmDelete = confirm("Delete this player?");

    if (!confirmDelete) return;

    fetch(`${BASE_URL}/admin/deletePlayer/${id}`, {
        method: "DELETE"
    })
    .then(res => res.text())
    .then(msg => {
        loadPlayers(); // refresh list
    });
}


window.onload = function () {
    if (window.location.pathname.includes("admin")) {
        loadPlayers();
    }
};


// ❄️ Snowfall Animation
function createSnowflake() {
    const snowContainer = document.querySelector(".snow-container");
    if (!snowContainer) return;

    const snowflake = document.createElement("div");
    snowflake.classList.add("snowflake");
	snowflake.innerHTML = '<i class="fa-solid fa-snowflake"></i>';

    snowflake.style.left = Math.random() * window.innerWidth + "px";
    snowflake.style.fontSize = Math.random() * 10 + 10 + "px";
    snowflake.style.animationDuration = Math.random() * 5 + 5 + "s";
    snowflake.style.opacity = Math.random();

    snowContainer.appendChild(snowflake);

    setTimeout(() => {
        snowflake.remove();
    }, 10000);
}

// create snowflakes continuously
setInterval(createSnowflake, 50);


