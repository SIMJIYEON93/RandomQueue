let totalUsers = 3; // 총 사용자 수
let submittedUsers = 0;

function submitQuestion() {
    const nickname = document.getElementById('nickname').value;
    const question = document.getElementById('question').value;

    const data = { nickname, question };

    fetch('/api/questions/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
            submittedUsers++;
            if (submittedUsers === totalUsers) {
                document.getElementById('start-section').style.display = 'block';
            }
            document.getElementById('nickname').value = '';
            document.getElementById('question').value = '';
        })
        .catch(error => console.error('Error:', error));
}

function startRandomQuestion() {
    fetch('/api/questions/start-random', {
        method: 'POST',
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('result-section').style.display = 'block';
            const resultList = document.getElementById('result-list');
            resultList.innerHTML = '';

            data.forEach((userQuestion) => {
                const listItem = document.createElement('li');
                listItem.innerText = `Nickname: ${userQuestion.nickname}, Question: ${userQuestion.question}`;
                resultList.appendChild(listItem);
            });
        })
        .catch(error => console.error('Error:', error));
}
