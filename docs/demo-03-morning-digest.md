# Bölüm 3 — Sabah Digest'i

**Süre:** ~2-3 dk · **Slot:** Bölüm 2'den sonra, Slayt 10'dan önce

Sabah 09:00. Agent gece izledi, digest hazır. Slack yok — digest doğrudan
dashboard notification olarak düşüyor.

> **Sırayı bozma:** Bölüm 2'yi bundan ÖNCE çek. Orada fire eden
> HighCPUAlarm, digest'te "✅ auto-resolved" satırı olarak görünür.
> İki demoyu birbirine bağlayan tek şey bu — bedavaya geliyor, kaçırma.

## Hazırlık

1. Gerçek stale PR verisi üret (bir kez, kayıttan günler önce ideal):
   ```
   bash ops/scripts/seed-stale-prs.sh
   ```
   Açılan PR'lara reviewer atama, dokunma.

2. Cron'u kur:
   ```
   bash ops/scripts/cron-setup.sh
   kirocrew cron list      # job ID'yi not al
   ```

3. **Prova tetikle** (kayıt değil):
   ```
   kirocrew cron trigger <job-id>
   ```
   Kontrol: format bozuk mu, boş kategorilerde "✅ All clear" var mı,
   çıktı ekrana tek seferde sığıyor mu. Format debug'ını kayıtta yapma.

## Shot list

| # | Ekran | Süre | Kes noktası |
|---|-------|------|-------------|
| 1 | Schedule ekranı — cron satırı, `0 9 * * 1-5` | ~15sn | Satır net okununca |
| 2 | Terminal — `kirocrew cron trigger <id>` | ~10sn | Komut kabul edilince |
| 3 | Dashboard — tarama akışı (PR → alarm → cost) | ~40sn | Taramalar bitince |
| 4 | Digest notification — tam metin | ~25sn | Okunacak kadar bekle |
| 5 | Son kare — digest ekranda sabit | ~10sn | — |

## Anlatım notları

- **Klip 1:** "Bu bir cron. Hafta içi her sabah 09:00. Kimse tetiklemiyor."
- **Klip 2:** "Sunum için manuel tetikliyorum — normalde zaten çalışmış oluyor."
- **Klip 3:** "Açık PR'ları tarıyor, alarmları topluyor, maliyete bakıyor."
  → *"Bakın — dün gece çözdüğümüz HighCPUAlarm burada, auto-resolved."*
- **Klip 4:** "Sabah masaya oturduğunuzda sizi bekleyen şey." → **SUS.**
  3-4 saniye sessizlik, izleyici okusun.
- **Klip 5:** "Ekibiniz kahvaltısını yaparken, agent gecenin raporunu
  hazırlamıştı." (Slayt 12'nin kapanış cümlesiyle aynı — bilinçli tekrar.)

## Dürüstlük notu

`cron-setup.sh` içindeki prompt gerçek `gh` ve `aws` çağrıları yapıyor,
uydurmuyor. Cost Explorer hesapta yoksa agent "veri yok" diyecek —
bunu kesme, aksine güven veriyor.
