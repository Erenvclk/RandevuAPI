# Randevu API

Java ve Spring Boot ile geliştirdiğim randevu yönetimi REST API'si. Projeyi baştan sona tek başıma geliştiriyorum.

> **Durum:** Aktif geliştirme aşamasında, yeni özellikler ekleniyor. Müşteri ve hizmet endpoint'leri çalışıyor. Randevu endpoint'leri ve kimlik doğrulama henüz eklenmedi.

## Özellikler

- Müşteri ve hizmet için kayıt, getirme ve listeleme
- Katmanlı mimari: entity'ler dışarı açılmaz, istek ve cevaplar DTO (Java record) ile taşınır, dönüşümü mapper'lar yapar
- Aynı e-posta ile ikinci müşteri kaydı reddedilir
- Randevu veri modeli hazır: müşteri ve hizmetle ilişkili, başlangıç/bitiş zamanı ve durum bilgisi tutar

## API Uç Noktaları

| Metot | Yol | İstek | Cevap |
| --- | --- | --- | --- |
| POST | `/musteriler` | `MusteriRequest` | `MusteriResponse` |
| GET | `/musteriler` | | `MusteriResponse` listesi |
| GET | `/musteriler/{id}` | | `MusteriResponse` |
| POST | `/hizmetler` | `HizmetRequest` | `HizmetResponse` |
| GET | `/hizmetler` | | `HizmetResponse` listesi |
| GET | `/hizmetler/{id}` | | `HizmetResponse` |

Örnek istek:

```bash
curl -X POST http://localhost:8080/musteriler \
  -H "Content-Type: application/json" \
  -d '{"ad":"Ayşe","soyad":"Yılmaz","telefon":"05551234567","email":"ayse@example.com"}'
```

## Veri Modeli

| Entity | Alanlar |
| --- | --- |
| Musteri | ad, soyad, telefon, e-posta (benzersiz) |
| Hizmet | ad, süre (dakika), fiyat |
| Randevu | müşteri, hizmet, başlangıç, bitiş, durum |

Randevu durumları: `BEKLEMEDE`, `ONAYLANDI`, `IPTAL`

## Mimari

```
controller → service → repository → MySQL
     ↑
 DTO + mapper
```

| Paket | Görevi |
| --- | --- |
| `controller` | HTTP isteklerini karşılar, DTO alır ve döner |
| `service` | İş kuralları (örneğin e-posta tekrarı kontrolü) |
| `repository` | Spring Data JPA ile veritabanı erişimi |
| `entity` | JPA entity'leri |
| `dto` | İstek ve cevap modelleri (Java record) |
| `mapper` | Entity ile DTO arasında dönüşüm |
| `enums` | Randevu durumları |

## Teknolojiler

| Araç | Sürüm / Not |
| --- | --- |
| Java | 25 |
| Spring Boot | 4.1.1 |
| Spring Web MVC | REST API |
| Spring Data JPA | Veritabanı erişimi |
| MySQL | Veritabanı |
| Lombok | Boilerplate azaltma |
| Maven | Maven Wrapper (`mvnw`) ile gelir |

## Kurulum

Gereksinimler: JDK 25 ve çalışan bir MySQL sunucusu.

1. Repoyu klonla:

   ```bash
   git clone https://github.com/Erenvclk/RandevuAPI.git
   cd RandevuAPI
   ```

2. MySQL'de bir veritabanı oluştur:

   ```sql
   CREATE DATABASE randevu;
   ```

3. `src/main/resources/application.properties` dosyasını oluşturup bağlantı bilgilerini yaz (dosya güvenlik nedeniyle repoda tutulmaz):

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/randevu
   spring.datasource.username=kullanici_adi
   spring.datasource.password=sifre
   spring.jpa.hibernate.ddl-auto=update
   ```

4. Uygulamayı başlat:

   ```bash
   ./mvnw spring-boot:run
   ```

   Windows'ta `mvnw.cmd spring-boot:run` komutunu kullan.

## Yol Haritası

- [ ] Randevu oluşturma ve listeleme endpoint'leri
- [ ] Kimlik doğrulama ve yetkilendirme
