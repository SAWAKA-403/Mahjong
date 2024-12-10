document.addEventListener("DOMContentLoaded", () => {
    const grid = document.getElementById("grid");
    const message = document.getElementById("message");
    const startButton = document.getElementById("start");
  
    let currentNumber = 1;
    let isClickable = false; // クリック可能かどうかのフラグ
  
    // ゲーム開始
    startButton.addEventListener("click", () => {
      const numbers = generateNumbers();
      displayNumbers(numbers);
      message.textContent = "数字を覚えてください...";
      currentNumber = 1;
      isClickable = false; // クリックを一時無効化
  
      // 5秒後に数字を隠し、クリックを有効化
      setTimeout(() => {
        hideNumbers();
        message.textContent = "1から順番にクリックしてください！";
        isClickable = true;
      }, 5000); // 5秒
    });
  
    // 数字を生成
    function generateNumbers() {
      const numbers = [];
      for (let i = 1; i <= 9; i++) {
        numbers.push(i);
      }
      return shuffleArray(numbers);
    }
  
    // 配列をシャッフル
    function shuffleArray(array) {
      for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
      }
      return array;
    }
  
    // 数字を表示
    function displayNumbers(numbers) {
      grid.innerHTML = "";
      numbers.forEach((number) => {
        const cell = document.createElement("div");
        cell.textContent = number;
        cell.dataset.number = number;
        grid.appendChild(cell);
  
        // クリックイベントを設定
        cell.addEventListener("click", () => {
          if (!isClickable) return; // クリック無効時は何もしない
  
          // クリックしたセルの数字を再表示
          if (!cell.classList.contains("revealed")) {
            cell.textContent = cell.dataset.number;
            cell.classList.add("revealed");
  
            if (parseInt(cell.dataset.number) === currentNumber) {
              cell.classList.add("correct");
              currentNumber++;
              if (currentNumber > 9) {
                message.textContent = "成功！全て正しくクリックしました！";
              }
            } else {
              cell.classList.add("incorrect");
              message.textContent = "不正解！残りの答えを表示します。";
              showRemainingAnswers(); // 残りの正解を表示
              disableGrid();
            }
          }
        });
      });
    }
  
    // 数字を隠す
    function hideNumbers() {
      Array.from(grid.children).forEach((cell) => {
        cell.textContent = ""; // 数字を隠す
      });
    }
  
    // 残りの正解を表示
    function showRemainingAnswers() {
      Array.from(grid.children).forEach((cell) => {
        if (!cell.classList.contains("revealed")) {
          cell.textContent = cell.dataset.number; // 正しい数字を表示
          cell.classList.add("revealed");
        }
      });
    }
  
    // 全てのセルを無効化
    function disableGrid() {
      Array.from(grid.children).forEach((cell) => {
        cell.style.pointerEvents = "none";
      });
    }
  });