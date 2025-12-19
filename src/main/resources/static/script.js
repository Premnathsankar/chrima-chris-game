// ADMIN



function startGame() {
    fetch(`/admin/startGame`, { method: "POST" })
        .then(res => res.text())
        .then(msg => alert(msg));
}

function resetGame() {
    fetch(`/admin/resetGame`, { method: "POST" })
        .then(res => res.text())
        .then(msg => alert(msg));
}

function fullResetGame() {
    const confirmReset = confirm(
        "This will delete ALL players and assignments. Are you sure?"
    );
    if (!confirmReset) return;

    fetch(`/admin/fullResetGame`, { method: "POST" })
        .then(res => res.text())
        .then(msg => {
            alert(msg);
            location.reload();
        });
}

function addPlayer() {
    const name = document.getElementById("name").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const msg = document.getElementById("adminMsg");

    if (name === "") {
        msg.innerText = "❌ Name is required";
        msg.style.color = "blue";
        return;
    }

    const phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(phone)) {
        msg.innerText = "❌ Phone number must be exactly 10 digits";
        msg.style.color = "blue";
        return;
    }

    fetch(`/admin/addPlayer`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, phone })
    })
    .then(res => res.text())
    .then(response => {
        msg.innerText = response;
        msg.style.color = response.includes("success") ? "lightgreen" : "red";

        if (response.includes("success")) {
            document.getElementById("name").value = "";
            document.getElementById("phone").value = "";
            loadPlayers();
        }
    });
}

function loadPlayers() {
    fetch(`/admin/players`)
        .then(res => res.json())
        .then(players => {
            const tbody = document.querySelector("#playerTable tbody");
            tbody.innerHTML = "";

            if (players.length === 0) {
                tbody.innerHTML = `<tr><td colspan="4">No players added</td></tr>`;
                return;
            }

            players.forEach((player, index) => {
                tbody.innerHTML += `
                    <tr>
                        <td>${index + 1}</td>
                        <td>${player.name}</td>
                        <td>${player.phone}</td>
                        <td>
                            <button onclick="deletePlayer(${player.id})">X</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function deletePlayer(id) {
    if (!confirm("Delete this player?")) return;

    fetch(`/admin/deletePlayer/${id}`, { method: "DELETE" })
        .then(() => loadPlayers());
}

// USER


window.onload = function () {
    if (window.location.pathname.includes("admin")) {
        loadPlayers();
    }
};

let currentPhone = "";
let receiverName = "";

function reveal() {
    const phone = document.getElementById("userPhone").value.trim();
    if (phone === "") {
        alert("Enter phone number");
        return;
    }

    currentPhone = phone;

    fetch(`/game/reveal?phone=${phone}`, { method: "POST" })
        .then(res => res.text())
        .then(result => {
            document.getElementById("revealResult").innerText = result;

            // ✅ FIXED CONDITION (emoji-safe)
            if (result.includes("You got:")) {
                receiverName = result.split("You got:")[1].trim();

                document.getElementById("assignTitle").innerText =
                    `Assign a task for ${receiverName}`;

                document.getElementById("taskAssignSection").classList.remove("hidden");
                document.getElementById("taskForMeSection").classList.remove("hidden");

                // ✅ LOAD BOTH TASK SECTIONS
                loadTaskForMe();
                loadMyAssignedTask();
            }
        });
}





// ASSIGN / EDIT TASK
function assignTask() {
    const task = document.getElementById("taskInput").value;

    fetch(`/game/assignTask?giverPhone=${currentPhone}&task=${encodeURIComponent(task)}`, {
        method: "POST"
    })
    .then(res => res.text())
    .then(msg => {
        document.getElementById("assignMsg").innerText = msg;
    });
}

// VIEW TASK ASSIGNED TO ME
function loadTaskForMe() {
    fetch(`/game/viewTask?phone=${currentPhone}`)
        .then(res => res.text())
        .then(task => {
            document.getElementById("taskForMeText").innerText = task;
        });
}


function loadMyAssignedTask() {
    fetch(`/game/myAssignedTask?phone=${currentPhone}`)
        .then(res => res.text())
        .then(task => {
            document.getElementById("taskInput").value = task || "";
        });
}

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



