// Lấy các phần tử cần thiết
var sliderWrapper = document.querySelector('.slider-wrapper');
var sliderMain = document.querySelector('.slider-main');
var sliderItems = document.querySelectorAll('.slider-item');
var sliderPrev = document.querySelector('.slider-prev');
var sliderNext = document.querySelector('.slider-next');
var sliderDots = document.querySelectorAll('.slider-dot-item');

// Khởi tạo biến đếm và số lượng ảnh
var currentIndex = 0;
var numItems = sliderItems.length;

// Hàm hiển thị ảnh tại vị trí currentIndex
function showItem() {
  sliderMain.style.transform = 'translateX(' + (-currentIndex * 100) + '%)';
  sliderDots.forEach(function(dot) {
    dot.classList.remove('active');
  });
  sliderDots[currentIndex].classList.add('active');
}

// Xử lý sự kiện click vào nút prev
sliderPrev.addEventListener('click', function() {
  currentIndex = (currentIndex === 0) ? (numItems - 1) : (currentIndex - 1);
  showItem();
});

// Xử lý sự kiện click vào nút next
sliderNext.addEventListener('click', function() {
  currentIndex = (currentIndex === numItems - 1) ? 0 : (currentIndex + 1);
  showItem();
});

// Xử lý sự kiện click vào điểm chỉ số
sliderDots.forEach(function(dot, index) {
  dot.addEventListener('click', function() {
    currentIndex = index;
    showItem();
  });
});

// Tự động chuyển đổi ảnh sau một khoảng thời gian nhất định
setInterval(function() {
  currentIndex = (currentIndex === numItems - 1) ? 0 : (currentIndex + 1);
  showItem();
}, 5000);

// Hiển thị ảnh đầu tiên
showItem();
