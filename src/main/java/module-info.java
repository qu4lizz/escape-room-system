module escape_room {
    requires name.remal.gradle_plugins.lombok;

    opens qu4lizz.escape_room.users to name.remal.gradle_plugins.lombok;
    exports qu4lizz.escape_room.users;

}
