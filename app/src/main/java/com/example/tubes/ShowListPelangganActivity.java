package com.example.tubes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tubes.API.ApiClient;
import com.example.tubes.API.ApiInterface;
import com.example.tubes.API.BookingDAO;
import com.example.tubes.API.UserResponse;
import com.example.tubes.PDF.PdfViewModel;
import com.example.tubes.adapter.PelangganRecyclerAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowListPelangganActivity extends AppCompatActivity {
    private ImageButton ibBack;
    private RecyclerView recyclerView;
    private PelangganRecyclerAdapter recyclerAdapter;
    private List<BookingDAO> user = new ArrayList<>();
    private List<BookingDAO> pelanggan;
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ShimmerFrameLayout shimmerFrameLayout;

    private PdfViewModel pdfViewModel;
    private static final String TAG = "PdfCreatorActivity";
    final private int REQUEST_CODE_ASK_PERMISSIONS = 101;
    private File pdfFile;
    private PdfWriter writer;
    private AlertDialog.Builder builder;
    private MaterialButton btn_create_pdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_list_pelanggan);
        pdfViewModel = new ViewModelProvider(this).get(PdfViewModel.class);

        shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        shimmerFrameLayout.startShimmer();

        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        btn_create_pdf = findViewById(R.id.btn_create_pdf);

        searchView = findViewById(R.id.searchUser);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setRefreshing(true);

        loadUser();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadUser();
            }
        });
    }

    public void loadUser() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> call = apiService.getAllPelanggan("data");

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                pelanggan = new ArrayList<>();
                pelanggan = response.body ().getPelanggans();
                generateDataList(response.body().getPelanggans());
                swipeRefreshLayout.setRefreshing(false);

//                btn_create_pdf.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        builder = new AlertDialog.Builder ( ShowListPelangganActivity.this);
//
//                        builder.setCancelable ( false );
//                        builder.setMessage ( "Apakah anda ingin mencetak surat?" );
//                        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                try {
//                                    createPdfWrapper ();
//                                }catch (FileNotFoundException e)
//                                {
//                                    e.printStackTrace ();
//                                    Toast.makeText(ShowListPelangganActivity.this, "blafe", Toast.LENGTH_SHORT).show();
//                                }catch (DocumentException e){
//                                    Toast.makeText(ShowListPelangganActivity.this, "blala", Toast.LENGTH_SHORT).show();
//                                    e.printStackTrace ();
//                                }
//                            }
//                        });
//                        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//                        AlertDialog dialog = builder.create ();
//                        dialog.show ();
//                    }
//                });
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(ShowListPelangganActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

//    private void createPdfWrapper() throws FileNotFoundException, DocumentException{
//
//        int hasWriteStoragePermission = 0;
//        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
//                    showMessageOKCancel("Izinkan aplikasi untuk akses penyimpanan?",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_ASK_PERMISSIONS);
//                                    }
//                                }
//                            });
//                    return;
//                }
//                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_ASK_PERMISSIONS);
//            }
//            return;
//        } else{
//            createPdf();
//        }
//    }

//    private void createPdf() throws FileNotFoundException, DocumentException{
//        File docsFolder= new File(Environment.getExternalStorageDirectory() + "/Download/");
//        if (!docsFolder.exists()) {
//            docsFolder.mkdir();
//            Log.i(TAG, "Direktori baru untuk file pdf berhasil dibuat");
//        }
//
//        String pdfname = "Queen Hotel"+".pdf";
//        pdfFile = new File(docsFolder.getAbsolutePath(), pdfname);
//        OutputStream output = new FileOutputStream(pdfFile);
//        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
//        writer = PdfWriter.getInstance(document, output);
//        document.open();
//
//        Paragraph judul = new Paragraph(" PELANGGAN QUEEN HOTEL \n\n", new
//                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 16,
//                com.itextpdf.text.Font.BOLD, BaseColor.BLACK));
//        judul.setAlignment(Element.ALIGN_CENTER);
//        document.add(judul);
//        PdfPTable tables = new PdfPTable(new float[]{16, 8});
//        tables.getDefaultCell().setFixedHeight(50);
//        tables.setTotalWidth(PageSize.A4.getWidth());
//        tables.setWidthPercentage(100);
//        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//        PdfPCell cellSupplier = new PdfPCell();
//        cellSupplier.setPaddingLeft(20);
//        cellSupplier.setPaddingBottom(10);
//        cellSupplier.setBorder(Rectangle.NO_BORDER);
//
//        Paragraph Kepada= new Paragraph(
//                "Kepada Yth : \n" + "Staff Queen Hotel"+"\n",
//                new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
//                        com.itextpdf.text.Font.NORMAL, BaseColor.BLACK));
//        cellSupplier.addElement(Kepada);
//        tables.addCell(cellSupplier);
//        PdfPCell cellPO = new PdfPCell();
//
//        Paragraph NomorTanggal = new Paragraph(
//                "No : " + "123456" + "\n\n" +
//                        "Tanggal : " + "5 Desember 2020" + "\n",
//                new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10, com.itextpdf.text.Font.NORMAL, BaseColor.BLACK)
//        );
//        NomorTanggal.setPaddingTop(5);
//        tables.addCell(NomorTanggal);
//        document.add(tables);
//        com.itextpdf.text.Font f = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10, com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);Paragraph Pembuka = new Paragraph("\nBerikut merupakan daftar pelanggan Queen Hotel \n\n",f);
//        Pembuka.setIndentationLeft(20);
//        document.add(Pembuka);
//        PdfPTable tableHeader = new PdfPTable(new float[]{5,5,5});
//        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
//        tableHeader.getDefaultCell().setFixedHeight(30);
//        tableHeader.setTotalWidth(PageSize.A4.getWidth());
//        tableHeader.setWidthPercentage(100);
//
//        PdfPCell h1 = new PdfPCell(new Phrase("Nama"));
//        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        h1.setPaddingBottom(5);
//        PdfPCell h2 = new PdfPCell(new Phrase("Tanggal Masuk"));
//        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
//        h2.setPaddingBottom(5);
//        PdfPCell h4 = new PdfPCell(new Phrase("Ruangan"));
//        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
//        h4.setPaddingBottom(5);
//        tableHeader.addCell(h1);
//        tableHeader.addCell(h2);
//        tableHeader.addCell(h4);
//        PdfPCell[] cells = tableHeader.getRow(0).getCells();
//        for (int j = 0; j < cells.length; j++) {
//            cells[j].setBackgroundColor(BaseColor.GRAY);
//        }
//        document.add(tableHeader);
//        PdfPTable tableData = new PdfPTable(new float[]{5,5,5});
//        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableData.getDefaultCell().setFixedHeight(30);
//        tableData.setTotalWidth(PageSize.A4.getWidth());
//        tableData.setWidthPercentage(100);
//        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
//        int arrLength = pelanggan.size();
//        for(int x=1;x<arrLength;x++){
//            for(int i=0;i<cells.length;i++){
//                if(i==0){
//                    tableData.addCell(String.valueOf(pelanggan.get ( x ).getNama_pelanggan ()));
//                }else if(i==1){
//                    tableData.addCell(pelanggan.get ( x ).getDate ());
//                }else{
//                    tableData.addCell("Rp"+pelanggan.get ( x ).getRoom ());
//                }
//            }
//        }
//        document.add(tableData);
//        com.itextpdf.text.Font h = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10, com.itextpdf.text.Font.NORMAL);
//        Date currentTime = Calendar.getInstance().getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
//        String tglDicetak = sdf.format(currentTime);
//        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
//        P.setAlignment(Element.ALIGN_RIGHT);
//        document.add(P);
//        document.close();
//        previewPdf();
//    }

//    private void previewPdf() {
//        PackageManager packageManager = getApplicationContext(). getPackageManager();
//        Intent testIntent = new Intent(Intent.ACTION_VIEW);
//        testIntent.setType("application/pdf");
//        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
//        if (list.size() > 0) {
//            Uri uri;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//                uri = FileProvider.getUriForFile(ShowListPelangganActivity.this, getPackageName()+".provider", pdfFile);
//
//            }else{
//                uri = Uri.fromFile(pdfFile);
//            }
//            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//            pdfIntent.setDataAndType(uri, "application/pdf");
//            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            pdfIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            pdfIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
//            pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//            getApplicationContext().grantUriPermission("com.example.tubes", uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);startActivity(pdfIntent);
//            startActivity(pdfIntent);
//        } else{
//            FancyToast.makeText(ShowListPelangganActivity.this,"Unduh pembuka PDF untuk menampilkan file ini", FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show();
//        }
//    }

//    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
//        new AlertDialog.Builder(ShowListPelangganActivity.this)
//                .setMessage(message)
//                .setPositiveButton("OK", okListener)
//                .setNegativeButton("Cancel", null)
//                .create()
//                .show();
//    }

    private void generateDataList(List<BookingDAO> customerList) {
        recyclerView = findViewById(R.id.userRecylerView);
        recyclerAdapter = new PelangganRecyclerAdapter(this, customerList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                recyclerAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerAdapter.getFilter().filter(s);
                return false;
            }
        });
    }
}