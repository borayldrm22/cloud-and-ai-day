# Kayıt Checklist'i

## Ortam (bir kez)

- [ ] OBS kurulu, çözünürlük **1920x1080**, 30fps
- [ ] Ayrı Scene'ler: `GitHub` / `Dashboard` / `Terminal` / `AWS Console`
      (alt-tab yapma — scene switch yap)
- [ ] Tarayıcıda bookmark bar kapalı, sekmeler temiz
- [ ] Terminal fontu büyütülmüş (en az 16pt), tema koyu
- [ ] Bildirimler kapalı (Do Not Disturb)
- [ ] Mikrofon testi yapıldı, arka plan sesi yok

## Çekim sırası

1. **Bölüm 1** — PR babysit (en kontrollü, ısınmak için iyi)
2. **Bölüm 2** — Alarm handler (gerçek AWS'ye bağımlı, en kırılgan;
   ayrı gün / sakin kafayla çek)
3. **Bölüm 3** — Morning digest (Bölüm 2'den SONRA, auto-resolved
   satırının digest'te görünmesi için)

## Çekim tekniği

- Tek continuous take yapma. Her shot ayrı klip.
- Her klip başında 2sn, sonunda 1-2sn boşluk bırak (kesim payı).
- Klipler arasında dur, bir sonraki cümleye bak, sonra devam et.
- Yanlış konuştuysan kesme — dur, 2sn bekle, cümleyi baştan al.
  Post'ta temiz olanı seçersin.

## Post-production

- [ ] Bölümler arası 0.5sn siyah geçiş
- [ ] Hızlandırılmış kısımlara köşe badge: `⏩ 45sn → 8sn`
- [ ] Bölüm 2'de "03:17" saatini büyük fontla vurgula
- [ ] Background müzik YOK — sadece ekran + ses
- [ ] Toplam süre kontrolü: 3 + 4 + 3 = ~9-10 dk (slot 17:00-22:00)

## Sunum metninde kontrol et

- [ ] Slack referansları temizlendi (Slack entegrasyonu yok —
      "dashboard'a raporluyor" de)
- [ ] Slayt 9'daki "gerçek aksiyonlar" iddiası demo içeriğiyle tutarlı
- [ ] PR numaraları slayttaki metinle eşleşiyor (ya da metin genelleştirildi)
