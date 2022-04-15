package com.example.gifslistapitestwork;

import java.util.Map;

public class GifElement {

    static int instanceCounter = 1;
    private int creation_id;
    private String type;
    private String id;
    private String url;
    private String slug;
    private String bitly_gif_url;
    private String bitly_url;
    private String embed_url;
    private String username;
    private String source;
    private String title;
    private String rating;
    private String content_url;
    private String source_tld;
    private String source_post_url;
    private boolean isSticker;
    private String import_datetime;
    private String trending_datetime;
    private Map<String, String> imageArray;
    private Original original;

    public GifElement(
                      String type,
                      String id,
                      String url,
                      String slug,
                      String bitlyGifUrl,
                      String bitlyUrl,
                      String embedUrl,
                      String username,
                      String source,
                      String title,
                      String rating,
                      String contentUrl,
                      String sourceTld,
                      String sourcePostUrl,
                      int isSticker,
                      String importTime,
                      String trendingTime,
                      String imageName,
                      String height,
                      String width,
                      String size,
                      String imageUrl
    ) {
        this.creation_id = instanceCounter++;
        this.type = type;
        this.id = id;
        this.url = url;
        this.slug = slug;
        this.bitly_gif_url = bitlyGifUrl;
        this.bitly_url = bitlyUrl;
        this.embed_url = embedUrl;
        this.username = username;
        this.source = source;
        this.title = title;
        this.rating = rating;
        this.content_url = contentUrl;
        this.source_tld = sourceTld;
        this.source_post_url = sourcePostUrl;
        this.isSticker = isSticker == 1;
        this.import_datetime = importTime;
        this.trending_datetime = trendingTime;
        this.original = new Original();
        this.original.name = imageName;
        this.original.height = Integer.parseInt(height);
        this.original.width = Integer.parseInt(width);
        this.original.size = Integer.parseInt(size);
        this.original.url = imageUrl;
    }

    public static int getInstanceCounter() {
        return instanceCounter;
    }

    public int getCreation_id() {
        return creation_id;
    }

    public void setCreation_id(int creation_id) {
        this.creation_id = creation_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBitly_gif_url() {
        return bitly_gif_url;
    }

    public void setBitly_gif_url(String bitly_gif_url) {
        this.bitly_gif_url = bitly_gif_url;
    }

    public String getBitly_url() {
        return bitly_url;
    }

    public void setBitly_url(String bitly_url) {
        this.bitly_url = bitly_url;
    }

    public String getEmbed_url() {
        return embed_url;
    }

    public void setEmbed_url(String embed_url) {
        this.embed_url = embed_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getSource_tld() {
        return source_tld;
    }

    public void setSource_tld(String source_tld) {
        this.source_tld = source_tld;
    }

    public String getSource_post_url() {
        return source_post_url;
    }

    public void setSource_post_url(String source_post_url) {
        this.source_post_url = source_post_url;
    }

    public boolean isSticker() {
        return isSticker;
    }

    public void setSticker(boolean sticker) {
        isSticker = sticker;
    }

    public String getImport_datetime() {
        return import_datetime;
    }

    public void setImport_datetime(String import_datetime) {
        this.import_datetime = import_datetime;
    }

    public String getTrending_datetime() {
        return trending_datetime;
    }

    public void setTrending_datetime(String trending_datetime) {
        this.trending_datetime = trending_datetime;
    }

    public Map<String, String> getImageArray() {
        return imageArray;
    }

    public void setImageArray(Map<String, String> imageArray) {
        this.imageArray = imageArray;
    }

    public Original getOriginal() {
        return original;
    }

    public void setOriginal(Original original) {
        this.original = original;
    }

    public class Original{
        public String name;
        public int height;
        public int width;
        public int size;
        public String url;
        public String mp4_size;
        public String mp4;
        public String webp_size;
        public String webp;
        public String frames;
        public String hash;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMp4_size() {
            return mp4_size;
        }

        public void setMp4_size(String mp4_size) {
            this.mp4_size = mp4_size;
        }

        public String getMp4() {
            return mp4;
        }

        public void setMp4(String mp4) {
            this.mp4 = mp4;
        }

        public String getWebp_size() {
            return webp_size;
        }

        public void setWebp_size(String webp_size) {
            this.webp_size = webp_size;
        }

        public String getWebp() {
            return webp;
        }

        public void setWebp(String webp) {
            this.webp = webp;
        }

        public String getFrames() {
            return frames;
        }

        public void setFrames(String frames) {
            this.frames = frames;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

    }
}
