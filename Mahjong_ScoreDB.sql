PGDMP          
            |            Mahjong_Score    16.4    16.4     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16786    Mahjong_Score    DATABASE     �   CREATE DATABASE "Mahjong_Score" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Japanese_Japan.932';
    DROP DATABASE "Mahjong_Score";
                postgres    false            �            1259    16788    game_records    TABLE     Q  CREATE TABLE public.game_records (
    id integer NOT NULL,
    game_type integer NOT NULL,
    rank integer NOT NULL,
    profit numeric(10,2) NOT NULL,
    tip numeric(10,2) DEFAULT 0,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT game_records_rank_check CHECK (((rank >= 1) AND (rank <= 4)))
);
     DROP TABLE public.game_records;
       public         heap    postgres    false            �            1259    16787    game_records_id_seq    SEQUENCE     �   CREATE SEQUENCE public.game_records_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.game_records_id_seq;
       public          postgres    false    216            �           0    0    game_records_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.game_records_id_seq OWNED BY public.game_records.id;
          public          postgres    false    215            P           2604    16791    game_records id    DEFAULT     r   ALTER TABLE ONLY public.game_records ALTER COLUMN id SET DEFAULT nextval('public.game_records_id_seq'::regclass);
 >   ALTER TABLE public.game_records ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            �          0    16788    game_records 
   TABLE DATA           T   COPY public.game_records (id, game_type, rank, profit, tip, created_at) FROM stdin;
    public          postgres    false    216   �       �           0    0    game_records_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.game_records_id_seq', 26, true);
          public          postgres    false    215            U           2606    16796    game_records game_records_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.game_records
    ADD CONSTRAINT game_records_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.game_records DROP CONSTRAINT game_records_pkey;
       public            postgres    false    216            �   f  x���Kn�0D���@�������I+�R�M6~�=�8����;YN���2U�Ҋ�i>�w�)Z�r��ڲ��Q�[�ƙ�q���#��!�]�6�q99Ew�"h��	���UQ)t��M�:�p��2���n�m���h0-�7����a�Á���1cS�����Ա�50U�Xo�v�d��V�n�F�g��p-��f�wWE���G~����Mi 睥����
�,��n���Q\W6�kNy6�ϕ���,�B�]���1�RX���J/^��o�e�O��k?D�+���fa�h� ڸ��,,��<��m�yz����oH������N_����۫\����z���Ĺ'Y����
'Ԣ     