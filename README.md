# Reverse Engineering Challenges & Writeups

Tổng hợp các **Reverse Engineering Challenge** cùng với quá trình phân tích và writeup tương ứng.

Mỗi thư mục trong repository đại diện cho **một challenge riêng biệt**, bao gồm file challenge và writeup mô tả quá trình reverse, phân tích và giải quyết challenge kèm chương trình cần phân tích.

## Cấu trúc Repository

```text
.
├── README.md
│
├── Challenge01/
│   ├── challenge.zip
│   └── writeup.ctb
└── ...
```

## Nội dung Writeup

Mỗi writeup tập trung vào quá trình phân tích challenge, thường bao gồm:

* Giới thiệu challenge
* Phân tích file ban đầu
* Xác định loại file và môi trường thực thi
* Static Analysis
* Decompile / Disassemble
* Phân tích control flow
* Phân tích các hàm quan trọng
* Phân tích thuật toán kiểm tra
* Phân tích mã hóa / giải mã
* Phân tích obfuscation hoặc protection
* Quá trình tìm flag / key / password
* Cách giải challenge
* Kết quả

## Công cụ sử dụng

Tùy thuộc vào từng challenge, có thể sử dụng các công cụ:

### Phân tích tổng quát

* `file`
* `strings`
* `xxd`
* `hexdump`
* `binwalk`

### Java

* Ghidra
* JADX
* JD-GUI

### .NET

* dnSpyEx
* ILSpy
* dotPeek

### Native / PE / ELF

* Ghidra
* x64dbg
* Rizin
* radare2
* Cutter
* `objdump`
* `readelf`
* `nm`

### Android

* JADX
* apktool
* baksmali / smali
* Frida

### Các công cụ hỗ trợ

* Python
* Bash
* Git
* UPX

## Mục tiêu

Repository này được tạo nhằm lưu trữ quá trình học tập và thực hành **Reverse Engineering** thông qua các challenge.

Mục tiêu của writeup không chỉ là tìm ra đáp án cuối cùng mà còn giải thích:

> **Chương trình hoạt động như thế nào và tại sao có thể giải được challenge.**

Qua đó xây dựng một tài liệu tham khảo cho việc học và nghiên cứu Reverse Engineering.

