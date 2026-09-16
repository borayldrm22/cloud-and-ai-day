# Bölüm 2 — Gece Alarm Handler

**Süre:** ~3-4 dk · **Slot:** Bölüm 1'den sonra

Saat 03:17. CloudWatch alarmı. Agent uyanıyor, sen uyumaya devam ediyorsun.

## Hazırlık

```
bash ops/scripts/setup-alarm.sh     # alarmı bir kez oluştur
```

`ops/playbooks/high-cpu-playbook.md` zaten repoda — agent bunu okuyacak.

Agent job'u (dashboard → Schedule, manuel tetiklemeli):

> CloudWatch'ta HighCPUAlarm ALARM durumunda.
> `ops/playbooks/high-cpu-playbook.md` dosyasını oku, adımları uygula,
> sonucu raporla.

## Shot list

| # | Ekran | Kes noktası |
|---|-------|-------------|
| 1 | CloudWatch konsolu — alarm OK | Konsol yüklenince |
| 2 | Terminal — `bash ops/scripts/simulate-high-cpu.sh` | Komut dönünce |
| 3 | Konsol — alarm ALARM'a geçti (kırmızı) | Renk değişince |
| 4 | Dashboard — job'u manuel tetikle | Tetikleme onaylanınca |
| 5 | Dashboard — agent playbook okuyor, adımları uyguluyor | **En uzun klip**, sabırlı çek |
| 6 | Konsol — aksiyonun etkisi (ASG capacity) | Değişiklik görününce |
| 7 | Agent özet raporu | — |

## AWS hesabı yoksa — fallback

Playbook zaten repoda. Agent'a şunu ver:

> HighCPUAlarm ALARM durumuna geçti. `ops/playbooks/high-cpu-playbook.md`
> dosyasını oku ve adımları simüle et. AWS çağrısı yapma.

Görsel etki neredeyse aynı. Ama anlatırken "bu kısım simülasyon" de —
Slayt 9'da "gerçek aksiyonlar" diyorsun, tutarlı kal.

## Takes arası

```
bash ops/scripts/reset-alarm.sh
```
