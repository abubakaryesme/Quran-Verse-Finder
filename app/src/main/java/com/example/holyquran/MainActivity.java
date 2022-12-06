package com.example.holyquran;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;



import com.example.holyquran.databinding.ActivityMainBinding;

import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    FloatingActionButton btnOneAdd;
    FloatingActionButton btnOneSub;
    FloatingActionButton btnSearch;
    EditText surahNo;
    EditText verseNo;
    TextView surahNameUrdu;
    TextView surahNameEng;
    TextView verseText;
    TextView errorSetter;

    int verseNumber = -1, surahNumber = -1, actualVerseNumber = -1;

    QDH verseIndex;
    QuranArabicText arabicVerse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        btnOneAdd = findViewById(R.id.floatingActionButton3);
        btnOneSub = findViewById(R.id.floatingActionButton4);
        btnSearch = findViewById(R.id.floatingActionButton5);

        surahNo= findViewById(R.id.editTextSuraf);
        verseNo = findViewById(R.id.editTextVerse);

        surahNameUrdu = findViewById(R.id.textSurahNameUrdu);
        surahNameEng = findViewById(R.id.textSurahNameEng);
        verseText = findViewById(R.id.displayVerseText);
        errorSetter = findViewById(R.id.errorText);

        verseIndex = new QDH();
        arabicVerse = new QuranArabicText();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    verseNumber = Integer.parseInt(verseNo.getText().toString());
                    surahNumber = Integer.parseInt(surahNo.getText().toString());
                }
                catch (Exception ex)
                {

                }


                if (surahNumber <= 0 || surahNumber > 114 || verseNumber <= 0
                        || verseIndex.surahAyatCount[surahNumber - 1] < verseNumber )
                {
                    errorSetter.setText("Invalid Input!");

                    verseText.setText("");
                    surahNameUrdu.setText("_____");
                    surahNameEng.setText("______");
                }
                else
                {
                    errorSetter.setText("");
                    actualVerseNumber = verseIndex.SSP[surahNumber-1] + verseNumber -1;
                    verseText.setText(arabicVerse.QuranArabicText[actualVerseNumber]);
                    surahNameUrdu.setText(verseIndex.urduSurahNames[surahNumber-1]);
                    surahNameEng.setText(verseIndex.englishSurahNames[surahNumber-1]);
                }
            }
        });
    }

}