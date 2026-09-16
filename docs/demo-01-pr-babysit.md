# Bölüm 1 — PR'ı Babysit Et

**Süre:** ~3 dk · **Slot:** Slayt 9'dan sonra, ilk demo

Dev bozuk kod push'ladı, CI kırıldı. Agent kimseyi uyandırmadan düzeltiyor.

## Hazırlık

1. `bug/user-profile-npe` branch'i zaten repoda — kasıtlı NPE içeriyor.
2. PR'ı aç:
   ```
   gh pr create --base main --head bug/user-profile-npe \
     --title "feat: add user profile endpoint" \
     --body "Adds GET /api/users/{id}/profile"
   ```
3. CI'nin FAILED olmasını bekle. Log'da şunu görmelisin:
   ```
   java.lang.NullPointerException
     at dev.clouddemo.api.service.UserService.buildProfile(UserService.java:84)
   ```
4. Agent'a `monitor_watch` kur:
   ```
   kind='github_pull_request'
   target='https://github.com/borayldrm22/cloud-and-ai-day/pull/<N>'
   objective='review_ready'
   wake_instructions='CI failed olduysa logu oku, root cause bul, fix yaz,
                      commit et, push et. Protected branch'e dokunma.'
   ```

## Shot list

| # | Ekran | Süre | Kes noktası |
|---|-------|------|-------------|
| 1 | GitHub PR sayfası — CI FAILED ❌ | ~10sn | Kırmızı X net görününce |
| 2 | Dashboard — "PR #N izleniyor" kartı | ~10sn | Kart yüklenince |
| 3 | Agent chat — log okuma → root cause | ~30sn | NPE satırı görününce |
| 4 | Diff kartı (null check) | ~15sn | Diff net görününce — **en iyi kare** |
| 5 | git commit / push çıktısı | ~10sn | Push bitince |
| 6 | CI yeniden koşuyor (hızlandırılacak) | ~15sn | — |
| 7 | CI PASSING ✅ + "MTTR: 4 dakika" | ~10sn | — |

## Beklenen fix

```java
User user = userService.findById(id);
+ if (user == null) return ResponseEntity.notFound().build();
return ResponseEntity.ok(userService.buildProfile(user));
```

Agent bunu service katmanına da yazabilir — ikisi de doğru. Anlatırken
"agent root cause'u buldu" de, "tam olarak şu satırı yazacak" deme.

## Çekim sonrası temizlik

```
git push origin --delete bug/user-profile-npe   # PR kapanır
git push origin bug/user-profile-npe            # tekrar çekim için geri koy
```
Branch'i lokalde sakla, tekrar çekim gerekirse hazır olsun.
