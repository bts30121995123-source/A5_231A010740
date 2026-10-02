package vn.edu.vhu.ltdd.a5intent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "A5_231A010740";

    private Contact contact;
    private EditText edtHoTenMoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "DetailActivity - onCreate");

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets bars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            bars.left,
                            bars.top,
                            bars.right,
                            bars.bottom);

                    return insets;
                });

        TextView tvThongTin =
                findViewById(R.id.tvThongTin);

        TextView tvNguoiGui =
                findViewById(R.id.tvNguoiGui);

        edtHoTenMoi =
                findViewById(R.id.edtHoTenMoi);

        Button btnLuu =
                findViewById(R.id.btnLuu);

        Button btnHuy =
                findViewById(R.id.btnHuy);

        // Nhận Contact từ màn hình 1
        contact =
                IntentCompat.getParcelableExtra(
                        getIntent(),
                        MainActivity.EXTRA_CONTACT,
                        Contact.class);

        // Nhận thông tin người gửi
        String nguoiGui =
                getIntent().getStringExtra(
                        MainActivity.EXTRA_NGUOI_GUI);

        // Kiểm tra có nhận được Contact hay không
        if (contact == null) {

            tvThongTin.setText(
                    R.string.no_data);

            Log.w(
                    TAG,
                    "Không nhận được Contact từ Intent");

            return;
        }

        // Hiển thị thông tin Contact
        tvThongTin.setText(
                getString(
                        R.string.detail_format,
                        contact.getHoTen(),
                        contact.getDienThoai(),
                        contact.getEmail()));

        tvNguoiGui.setText(
                getString(
                        R.string.sent_by,
                        nguoiGui));

        // Đưa họ tên hiện tại vào ô sửa
        edtHoTenMoi.setText(
                contact.getHoTen());

        // Nút Lưu
        btnLuu.setOnClickListener(
                v -> luuVaQuayLai());

        // Nút Hủy
        btnHuy.setOnClickListener(v -> {

            setResult(RESULT_CANCELED);

            Log.d(TAG,
                    "Người dùng bấm Hủy - RESULT_CANCELED");

            finish();
        });
    }

    // Lưu tên mới và trả Contact về màn hình 1
    private void luuVaQuayLai() {

        String hoTenMoi =
                edtHoTenMoi.getText()
                        .toString()
                        .trim();

        if (hoTenMoi.isEmpty()) {

            edtHoTenMoi.setError(
                    getString(
                            R.string.err_empty));

            return;
        }

        // Cập nhật tên mới
        contact.setHoTen(hoTenMoi);

        // Tạo Intent chứa kết quả
        Intent ketQua =
                new Intent();

        ketQua.putExtra(
                MainActivity.EXTRA_CONTACT,
                contact);

        // Báo kết quả thành công
        setResult(
                RESULT_OK,
                ketQua);

        Log.d(
                TAG,
                "Trả kết quả về: "
                        + hoTenMoi);

        // Đóng màn hình 2
        finish();
    }

    // =========================
    // VÒNG ĐỜI DETAIL ACTIVITY
    // =========================

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "DetailActivity - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "DetailActivity - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "DetailActivity - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "DetailActivity - onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "DetailActivity - onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "DetailActivity - onDestroy");
    }
}