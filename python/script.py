import psycopg2
import json

try:
    connection = psycopg2.connect(user="jumbo", password="jumbo", host="db", port="5432", database="jumbo")
    cursor = connection.cursor()
    with open('stores.json') as file:
        for store in json.load(file)['stores']:
            insert_query =  """INSERT INTO public.stores (city, postal_code, street, street2, street3, address_name, uuid, geometry,
                                complex_number, show_warning_message, today_open, location_type, collection_point, sap_store_id, today_close) 
                               VALUES (%s, %s, %s, %s, %s, %s, %s, ST_SetSRID(ST_MakePoint(%s, %s), 4326), %s, %s, %s, %s, %s, %s, %s)"""
            cursor.execute(insert_query, (store['city'], store['postalCode'], store['street'], store['street2'], store['street3'],
                                          store['addressName'], store['uuid'], store['longitude'], store['latitude'], store['complexNumber'],
                                          store['showWarningMessage'], store['todayOpen'], store['locationType'], store.get('collectionPoint', None), store['sapStoreID'],
                                          store['todayClose']))
    connection.commit()
except (Exception, psycopg2.Error) as error:
    print("Error while connecting to PostgreSQL", error)
    exit(1)
finally:
    if connection:
        cursor.close()
        connection.close()
        print("PostgreSQL connection is closed")