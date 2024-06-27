# UYGULAMA NOTLARIM

## Docker

        Projemizde docker üzerinde gerekli kurulumları gerçekleştireceğiz.
    bu nedenle ilk olarak dockerdesktop kurulumunu yapınız. 

## MongoDB İşlemleri

        MongoDB kurulumu bir kaç aşamadan oluşacak ve bu DB yi yönetmek içni 
    gerekli tool ları kurmamız gerekecek. İlk olarak mongoDB yi kuralım.

```bash
docker run --name HSMongo -d -p 27017:27017 -e "MONGO_INITDB_ROOT_USERNAME=BilgeAdmin" -e "MONGO_INITDB_ROOT_PASSWORD=Aa123456**" mongo:latest
```

        Yukarıdaki komut terminalde çalıştırıldıktan sonra mongoDB default 
    ayarları ile çalışamaya başlayacaktır.

        MongoDB Compass Tool kurularak bağlantı sağlanır ve bu bağlantı üzerindenm
    gerekli konfiglerin yapılması gereklidir. 
    1- Öncelikle Root Admin ile ilgili DB ye bağlanıyoruz;
    1.1. New Connection
    1.2. localhost:27017 şeklinde ilgili PC ip adresi ve portu girilir.
    1.3. Authentication kısmından username/password seçilerek root
    bilgileri girilir. 
    DİKKAT!!! burada Database adı girilmez çünkü root kullanıcısıdır.
    2- DB ler oluşturulur ve bunlara kullanıcılar tanımlanır.
    2.1. sol üst tarafta bulunan + simgesine basarak yeni bir DB oluşturma
    ekranına geçilir. Burada ad ve index adı verilerek oluşturma tamamlanır.
    2.2. DB yi yönetecek olan yeni bir kullanıcı ve yetilenfirmeleri tanımlanır.
    DİKKAT!!! Asla root kullanıcısını DB temel işlemleri(CRUD) için kullanmayın
    zaten mongoDB default olarak bu işlemlerin yapılmasını reddeder.
    2.3. Mongo compass içerisinden sol atta bulunan mongosh a tıklayıp
    terminal ekranını açıyoruz.
    2.4. Burada ilgili DB ye geçiş yapmak için "use" komutunu kullanıyoruz.
    > use UserProfileDB
    2.5. İlgili DB ye geçtikten sonra yeni bir yetkili kullanıcı oluşturuyoruz
    db.createUser(
        {
        user: "admin",
        pwd:  "root",   
        roles: ["readWrite", "dbAdmin"]
        }
    )

    db.createUser({user: "admin",pwd:  "root",roles: [ "readWrite", "dbAdmin"]})
    2.6 Buradan sonra root bağlantısından çıkarak yeniden bağlantı yapıyoruz.
    burada dikkat etmeniz gereken husus, artık bir kullanıcı bir DB ye yetkili
    kılınmış durumda bu nedenle bağlatı kısmında Db adının mutlaka yazılması
    gereklidir.

## MongoDB & Spring Boot Configleri


## Redis Docker

````bash
    docker run -d -p 6379:6379 redis 
````

    
