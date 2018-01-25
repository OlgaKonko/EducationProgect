package constants;

import model.pet.Tag;

public enum DefaultTags {
    Small(new Tag(45670, "small")),
    Big(new Tag(45671, "big")),
    White(new Tag(45672, "white")),
    Black(new Tag(45673, "black"));
    private Tag tag;


    DefaultTags(Tag tag) {
        this.tag = tag;
    }

    public Tag getTag() {
        return tag;
    }
}
