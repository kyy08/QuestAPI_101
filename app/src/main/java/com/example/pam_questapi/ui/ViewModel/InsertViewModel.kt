package com.example.pam_questapi.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_questapi.model.Mahasiswa
import com.example.pam_questapi.repository.MahasiswaRepository
import kotlinx.coroutines.launch

// ViewModel untuk mengatur data dan logika form tambah mahasiswa
class InsertViewModel(private val mhs: MahasiswaRepository) : ViewModel() {

    // Data untuk menyimpan keadaan form (seperti input dari pengguna)
    var uiState by mutableStateOf(InsertUiState())
        private set

    // Fungsi untuk mengubah data form ketika ada input dari pengguna
    fun updateInsertMhsState(update: (InsertUiEvent) -> InsertUiEvent) {
        uiState = uiState.copy(insertUiEvent = update(uiState.insertUiEvent))
    }

    // Fungsi untuk menambahkan data mahasiswa ke database
    fun insertMhs() {
        viewModelScope.launch { // Menjalankan proses di latar belakang (tidak mengganggu UI)
            if (isValidInput()) { // Validasi input sebelum menyimpan data
                try {
                    // Mengambil data dari form dan mengirimnya ke repository
                    mhs.insertMahasiswa(uiState.insertUiEvent.toMhs())
                } catch (e: Exception) {
                    e.printStackTrace() // Menangani error jika terjadi masalah
                }
            } else {
                println("Input tidak valid: Harap isi semua field dengan benar.")
            }
        }
    }

    // Fungsi untuk memvalidasi input pengguna
    fun isValidInput(): Boolean {
        val event = uiState.insertUiEvent
        return event.nim.isNotBlank() &&
                event.nama.isNotBlank() &&
                event.alamat.isNotBlank() &&
                event.jenisKelamin.isNotBlank() &&
                event.kelas.isNotBlank() &&
                event.angkatan.isNotBlank()
    }
}

// Menyimpan state form input mahasiswa
data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent() // State default berisi objek kosong dari InsertUiEvent
)

// Menyimpan data input pengguna untuk form mahasiswa
data class InsertUiEvent(
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jenisKelamin: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)

// Fungsi untuk mengubah data InsertUiEvent menjadi Mahasiswa
fun InsertUiEvent.toMhs(): Mahasiswa = Mahasiswa( // InsertUiEvent > Mahasiswa > Simpan data Mhs ke db
    nim = nim.trim(), // Memindahkan nilai NIM dari InsertUiEvent ke Mahasiswa
    nama = nama.trim(),
    alamat = alamat.trim(),
    jenisKelamin = jenisKelamin.trim(),
    kelas = kelas.trim(),
    angkatan = angkatan.trim()
)

// Fungsi untuk mengubah data Mahasiswa menjadi InsertUiState
fun Mahasiswa.toUiStateMhs(): InsertUiState = InsertUiState( // Mahasiswa > insertUiEvent > Masuk ke InsertUiState
    insertUiEvent = toInsertUiEvent() // Memanggil fungsi toInsertUiEvent untuk mengonversi data Mahasiswa
)

// Fungsi untuk mengubah data Mahasiswa menjadi data InsertUiEvent
fun Mahasiswa.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    nim = nim, // Memindahkan nilai NIM dari Mahasiswa ke InsertUiEvent
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)
