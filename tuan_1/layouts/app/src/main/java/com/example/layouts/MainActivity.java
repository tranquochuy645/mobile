package com.example.layouts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button switchLayoutButton; // Khai báo biến cho nút chuyển đổi layout
    private int index = 0; // Biến để theo dõi loại layout hiện tại
    private int[] layoutIds = {
            R.layout.constraint_layout,
            R.layout.relative_layout,
            R.layout.linear_layout,
            R.layout.frame_layout
    }; // Danh sách các ID của layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout); // Thiết lập layout ban đầu là constraint_layout

        switchLayoutButton = findViewById(R.id.button); // Tìm nút trong layout

        // Đặt trình nghe sự kiện click cho nút chuyển đổi
        switchLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchLayout(); // Gọi hàm để chuyển đổi layout
            }
        });
    }

    public void switchLayout() {
        // Thay đổi layout dựa trên biến index
        index = (index + 1) % 4;

        // Đặt layout mới bằng cách sử dụng ID từ danh sách layoutIds
        setContentView(layoutIds[index]);

        // Tìm lại nút trong layout mới để cập nhật trình nghe sự kiện click
        switchLayoutButton = findViewById(R.id.button);

        // Đặt trình nghe sự kiện click cho nút chuyển đổi trong layout mới
        switchLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchLayout(); // Gọi hàm để chuyển đổi layout
            }
        });
    }
}
