import random
from datetime import datetime, timedelta

import mysql.connector
import requests


def fetch_random_users(n):
    url = f'https://randomuser.me/api/?results={n}&inc=gender,name,email,phone,picture,location'
    response = requests.get(url)
    data = response.json()
    return data['results']


def random_start_date():
    return datetime.now() - timedelta(days=random.randint(1, 365))


mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="root",
    database="wemove"
)

myCursor = mydb.cursor()

users = fetch_random_users(500)

insert_INTO_member = "INSERT INTO member (firstname,lastname, email,gender,address,phone,picture,active) VALUES (%s, %s,%s, %s,%s,%s, %s,%s)"
insert_INTO_member_subscription = "INSERT INTO member_subscription (memberid,subscriptionid,startdate,paid) VALUES (%s, %s,%s, %s)"

activeUsers = 0.9 * 500
for user in users:
    firstname = user["name"]["first"]
    lastname = user["name"]["last"]
    email = user["email"]
    gender = user["gender"]
    address = user["location"]["street"]["name"] + " " + user["location"]["city"]
    phone = user["phone"]
    picture = user["picture"]["medium"]

    if activeUsers:
        active = 1
        activeUsers -= 1
    else:
        active = 0

    myCursor.execute(insert_INTO_member, (firstname, lastname, email, gender, address, phone, picture, active))
    memberId = myCursor.lastrowid

    yearlySub = 0.2 * 0.9 * 500
    monthlySub = 0.75 * 0.9 * 500
    dailySub = 0.05 * 0.9 * 500

    if active:
        if yearlySub:
            subscriptionID = 1
            yearlySub -= 1
            paid = 300
        elif monthlySub:
            subscriptionID = 2
            monthlySub -= 1
            paid = 30
        else:
            subscriptionID = 3
            dailySub -= 1
            paid = 7

        startDate = random_start_date()
        myCursor.execute(insert_INTO_member_subscription, (memberId, subscriptionID, startDate, paid))

myCursor.close()
mydb.commit()
