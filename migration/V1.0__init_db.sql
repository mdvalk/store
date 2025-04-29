CREATE TABLE IF NOT EXISTS public.stores
(
    city                 TEXT,
    postal_code          TEXT,
    street               TEXT,
    street2              TEXT,
    street3              TEXT,
    address_name         TEXT,
    uuid                 TEXT,
    geometry             GEOMETRY,
    complex_number       INTEGER,
    show_warning_message BOOLEAN,
    today_open           TEXT,
    location_type        TEXT,
    collection_point     BOOLEAN,
    sap_store_id         INTEGER,
    today_close          TEXT
);