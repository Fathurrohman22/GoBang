package id.gobang.app.Helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.Locale;

import id.gobang.app.R;
import naturalizer.separator.Separator;

public class Bantuan {
    private Context context;

    public Bantuan(Context context) {
        this.context = context;
    }

    public void toastShort(String pesan) {
        Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show();
    }

    public void toastLong(String pesan) {
        Toast.makeText(context, pesan, Toast.LENGTH_LONG).show();
    }

    public void swal_basic(String pesan) {
        new SweetAlertDialog(context)
                .setTitleText(pesan)
                .show();
    }

    public void swal_title(String title, String subtitle) {
        new SweetAlertDialog(context)
                .setTitleText(title)
                .setContentText(subtitle)
                .show();
    }

    public void swal_error(String pesan) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText(pesan)
                .show();
    }

    public void swal_warning(String pesan) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Peringatan")
                .setContentText(pesan)
                .show();
    }

    public void swal_sukses(String pesan) {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Peringatan")
                .setContentText(pesan)
                .show();
    }

    public String formatHarga(String s) {
        return Separator.getInstance().doSeparate(s, Locale.GERMANY);
    }

    public SweetAlertDialog swal_loading(String pesan) {
        SweetAlertDialog SAD = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        SAD.getProgressHelper().setBarColor(context.getResources().getColor(R.color.colorPrimary));
        SAD.setTitleText("Loading...");
        SAD.setContentText(pesan);
        SAD.setCancelable(false);

        return SAD;
    }

    public void alertDialogDebugging(String pesan) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Info Debugging")
                .setMessage(pesan)
                .setPositiveButton(android.R.string.yes, null)
                .setCancelable(false)
                .show();
    }

    public void alertDialogPeringatan(String pesan) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Peringatan")
                .setMessage(pesan)
                .setPositiveButton(android.R.string.yes, null)
                .setCancelable(false)
                .show();
    }

    public void alertDialogInformasi(String pesan) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Informasi")
                .setMessage(pesan)
                .setPositiveButton(android.R.string.yes, null)
                .setCancelable(false)
                .show();
    }

    public boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public boolean isAppInstalled(String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
